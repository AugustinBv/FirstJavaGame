package ch.epfl.cs107.play.signal.logic;

import ch.epfl.cs107.play.signal.Signal;

public interface Logic extends Signal {

	/**
	 * Default boolean isOn
	 * @return
	 */
	default boolean isOn() {
		return false;
	}

	/**
	 * default intensity
	 * @return (Float) : intensity of the signal
	 */
	default float getIntensity() {
		if (isOn()) {
			return 1.0f;
		} else {
			return 0.0f;
		}	
	}

	@Override
	default float getIntensity(float t) {
		return getIntensity() ;
	}



	Logic TRUE = new Logic() {
		@Override
		public boolean isOn() {
			return true;
		}
	};


	Logic FALSE = new Logic() {
		@Override
		public boolean isOn() {
			return false;
		}
	};	

}