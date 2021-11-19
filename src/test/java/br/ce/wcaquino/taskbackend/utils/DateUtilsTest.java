package br.ce.wcaquino.taskbackend.utils;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

public class DateUtilsTest {

	@Test
	public void deveRetornarTrueParaDataFutura() {
		LocalDate dataFutura = LocalDate.of(2023, 1, 1);
		Assert.assertTrue(DateUtils.isEqualOrFutureDate(dataFutura));
	}

	@Test
	public void deveRetornarFalseParaDataPassada() {
		LocalDate dataPassada = LocalDate.of(2013, 1, 1);
		Assert.assertFalse(DateUtils.isEqualOrFutureDate(dataPassada));
	}

	@Test
	public void deveRetornarTrueParaDataPresente() {
		LocalDate dataPresente = LocalDate.now();
		Assert.assertTrue(DateUtils.isEqualOrFutureDate(dataPresente));
	}
}
