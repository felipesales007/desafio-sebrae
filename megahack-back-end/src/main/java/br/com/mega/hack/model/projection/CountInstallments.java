package br.com.mega.hack.model.projection;

import java.time.LocalDate;

public interface CountInstallments {
	LocalDate getDateFather();
	Long getNumInstallments();
}
