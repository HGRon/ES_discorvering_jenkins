package dominio;

public class Resultado {

	private Participante participante;
	private double metrica;
	private int id;
	
	public Resultado(Participante participante, double metrica) {
		this.participante = participante;
		this.metrica = metrica;
	}

	public Participante getParticipante() {
		return participante;
	}

	public double getMetrica() {
		return metrica;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((participante == null) ? 0 : participante.hashCode());
		long temp;
		temp = Double.doubleToLongBits(metrica);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Resultado other = (Resultado) obj;
		if (participante == null) {
			if (other.participante != null)
				return false;
		} else if (!participante.equals(other.participante))
			return false;
		if (Double.doubleToLongBits(metrica) != Double
				.doubleToLongBits(other.metrica))
			return false;
		return true;
	}


	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	
}
