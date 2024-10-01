package port.output

import java.time.Clock

fun interface ClockService {
    fun clock(): Clock
}
