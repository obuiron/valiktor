package org.valiktor.i18n.formatters

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.entry
import org.valiktor.i18n.Formatters
import org.valiktor.i18n.SupportedLocales
import org.valiktor.i18n.formatAllSupportedLocales
import java.time.LocalDateTime
import java.time.Month
import kotlin.test.Test

class LocalDateTimeFormatterTest {

    @Test
    fun `should format dateTime`() {
        assertThat(Formatters[LocalDateTime::class].formatAllSupportedLocales(LocalDateTime.of(2018, Month.DECEMBER, 31, 23, 58, 59))).containsExactly(
            entry(SupportedLocales.DEFAULT, "Dec 31, 2018 11:58:59 PM"),
            entry(SupportedLocales.EN, "Dec 31, 2018 11:58:59 PM"),
            entry(SupportedLocales.PT_BR, "31/12/2018 23:58:59"))
    }
}