package unlam.progava.oia;

public class Calculadora {
	private int[] apps;
	private static int cantidadApps = 0;
	private int appsARemover = 0;
	private int mbNecesarios = 0;
	private int maximo = 0;

	public Calculadora(int cantApps) {
		this.apps = new int[cantApps];
	}

	public boolean agregar(int app) {
		if (app > maximo) {
			maximo = app;
		}
		apps[cantidadApps] = app;
		cantidadApps++;
		return true;
	}

	public int getResultado() {
		return appsARemover;
	}

	public void setMBNecesarios(int megas) {
		mbNecesarios = megas;
	}

	public void calcular() {
		final int MIN_TAM_GRUPO = 1;
		final int APPS_EN_CELU = apps.length;

		if (maximo >= mbNecesarios) {
			appsARemover = MIN_TAM_GRUPO;
			return;
		}

		int primerGrupoQueSupera = -1; // Indica cantidad, no cuales
		int[] acumMB = apps; // Solo para igualar lengths

		for (int i = 1; i < APPS_EN_CELU; i++) {
			acumMB[i] = apps[i] + acumMB[i - 1];
			if (primerGrupoQueSupera == -1 && acumMB[i] >= mbNecesarios)
				primerGrupoQueSupera = i + 1;
		}

		// A esta altura, ya encontre el un grupo que supera los MB de la nueva app
		appsARemover = primerGrupoQueSupera;

		primerGrupoQueSupera--; // Busco, si hay, grupos mas chicos que superen los MB.
		for (int tamGrupo = primerGrupoQueSupera; tamGrupo > MIN_TAM_GRUPO; tamGrupo--) {
			for (int k = 1; (k + tamGrupo) <= APPS_EN_CELU; k++) {
				if ((acumMB[k + tamGrupo - 1] - acumMB[k - 1]) >= mbNecesarios) {
					appsARemover = tamGrupo;
				}
			}
			if (appsARemover > tamGrupo) {
				return; // Si no encontre grupos de n apps, no sirve buscar grupos de n-1 apps.
			}
		}

	}

}