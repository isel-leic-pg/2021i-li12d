
/**
 * The replay mode
 */
enum class ReplayMode { FORWARD, BACKWARD }

/**
 * THe data representation required for replaying
 */
data class Replay(val mode: ReplayMode, val currentSnapshotIndex: Int)

/**
 * Extension function that builds a new [Replay] instance from the given arguments, defaulting to the original values.
 */
fun Replay.build(mode: ReplayMode = this.mode, currentSnapshotIndex: Int = this.currentSnapshotIndex) =
    Replay(mode, currentSnapshotIndex)

/**
 * The game mode.
 */
enum class Mode { PLAYING, REPLAYING, STOPPED }

/**
 * Representation of the game.
 *
 * @property world  th
 * @property history    the list of world snapshots that comprise the recorded game history
 * @property mode       the current game mode
 * @property replayInfo the replay information, if the game is in [Mode.REPLAYING] mode
 */
data class Game(val world: World, val history: List<World>, val mode: Mode, val replayInfo: Replay? = null)

/**
 * Extension function that builds a new game instance from the given arguments, defaulting to the original values.
 */
fun Game.build(
    world: World = this.world,
    history: List<World> = this.history,
    mode: Mode = this.mode,
    replayInfo: Replay? = this.replayInfo
) = Game(world, history, mode, replayInfo)


/**
 * Adds the given world snapshot to the game.
 *
 * @param game  the game
 * @param world the world snapshot
 * @return the new game instance
 */
fun addWorldSnapshotToGame(game: Game, world: World) = game.build(world = world, history = game.history + world)

/**
 * Computes the next game state if the game is in [Mode.REPLAYING], otherwise it returns the same game state.
 *
 * @param game  The current game state
 * @return The computed game state
 */
fun computeReplaySnapshot(game: Game): Game {

    fun nextIndex(info: Replay) =
        if (info.mode == ReplayMode.FORWARD) info.currentSnapshotIndex + 1
        else info.currentSnapshotIndex - 1

    fun isReplayOver(info: Replay, history: List<World>) =
        if (info.mode == ReplayMode.FORWARD) info.currentSnapshotIndex == history.size - 1
        else info.currentSnapshotIndex == 0

    return when {
        game.mode != Mode.REPLAYING || game.replayInfo == null -> game
        isReplayOver(game.replayInfo, game.history) -> game.build(
            mode = Mode.STOPPED,
            replayInfo = null
        )
        else -> game.build(
            world = game.history[nextIndex(game.replayInfo)],
            replayInfo = game.replayInfo.build(currentSnapshotIndex = nextIndex(game.replayInfo))
        )
    }
}