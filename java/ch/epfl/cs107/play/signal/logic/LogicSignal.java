package ch.epfl.cs107.play.signal.logic;



abstract class LogicSignal implements Logic {

	/**
	 * intensity getter
	 */
	public final float getIntensity() {
		if (isOn()) {
			return 1.0f;
		} else {
			return 0.0f;
		}	
	}

	@Override
	public final float getIntensity(float t) {
		return getIntensity() ;
	}



}