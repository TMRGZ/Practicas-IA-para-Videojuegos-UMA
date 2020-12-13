package reversi;

import java.util.Objects;

/**
 * Representación de las fichas para los juegos.
 *  
 * @author José Miguel Horcas Aguilera (2010)
 * 
 * @version 2010 / 2018
 *
 */
public class Ficha<E> {

	private E f;			// Representación de la ficha.
	
	
	/**
	 * Constructor.
	 * 
	 * @param f	Caracter que representa la ficha.
	 */
	public Ficha(E c) {
		this.f = c;
	}
	
	/**
	 * Selector.
	 * @return	Caracter que representa la ficha.
	 */
	public E f () {
		return f;
	}
	
//	public boolean equals (Object o) {
//		return o instanceof Ficha && this.f() == ((Ficha) o).f();
//	}
	
	public String toString () {
		return "" + f();
	}

	@Override
	public int hashCode() {
		return Objects.hash(f);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ficha other = (Ficha) obj;
		return f == other.f;
	}
}
