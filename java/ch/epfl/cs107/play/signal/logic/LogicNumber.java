package ch.epfl.cs107.play.signal.logic;

import java.util.LinkedList;
import java.util.List;

public class LogicNumber extends LogicSignal{

	private float nb;
	private List<Logic> e;

	/**
	 * LogicNumber 
	 * @param nb (Float) : wanted number after computing the signals
	 * @param e (Logic) : the signals 
	 */
	public LogicNumber(float nb, Logic...e) {
		this.nb = nb;
		this.e = new LinkedList<>();
		for (int i = 0 ; i < e.length ; ++i) {
			this.e.add(e[i]);
		}
	}


	@Override
	public boolean isOn() {

		if (e.size() > 12 || (nb < 0 || nb > Math.pow(2, e.size()))) {
			return false;
		}

		double nbSignal = 0;

		for (int i = 0 ; i < e.size() ; ++i) {
			nbSignal += e.get(i).getIntensity() * Math.pow(2, i);
		}

		if (nb == (float) nbSignal) {
			return true;
		}

		return false;
	}

}