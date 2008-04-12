package fr.studioshi.commons.game.keylistener;

public class Events {

	private static Events instance = new Events();

	private boolean keyLeftPressed;
	private boolean keyRightPressed;
	private boolean keyUpPressed;
	private boolean keyDownPressed;

	private boolean keyAButtonPressed;
	private boolean keyBButtonPressed;

	private boolean keyEscPressed;
	private boolean keySpacePressed;
	private boolean keyEnterPressed;

	public static Events getInstance() {
		return instance;
	}

	public static void setInstance(Events instance) {
		Events.instance = instance;
	}

	public boolean isKeyAButtonPressed() {
		return keyAButtonPressed;
	}

	public boolean isKeyBButtonPressed() {
		return keyBButtonPressed;
	}

	public boolean isKeyDownPressed() {
		return keyDownPressed;
	}

	public boolean isKeyEnterPressed() {
		return keyEnterPressed;
	}

	public boolean isKeyEscPressed() {
		return keyEscPressed;
	}

	public boolean isKeyLeftPressed() {
		return keyLeftPressed;
	}

	public boolean isKeyRightPressed() {
		return keyRightPressed;
	}

	public boolean isKeySpacePressed() {
		return keySpacePressed;
	}

	public boolean isKeyUpPressed() {
		return keyUpPressed;
	}

	public void reset() {
		keyLeftPressed = false;
		keyRightPressed = false;
		keyUpPressed = false;
		keyDownPressed = false;
		keyEscPressed = false;
		keyAButtonPressed = false;
		keyBButtonPressed = false;
		keySpacePressed = false;
		keyEnterPressed = false;
	}

	public void setKeyAButtonPressed(boolean keyAButtonPressed) {
		this.keyAButtonPressed = keyAButtonPressed;
	}

	public void setKeyBButtonPressed(boolean keyBButtonPressed) {
		this.keyBButtonPressed = keyBButtonPressed;
	}

	public void setKeyDownPressed(boolean keyDownPressed) {
		this.keyDownPressed = keyDownPressed;
	}

	public void setKeyEnterPressed(boolean keyEnterPressed) {
		this.keyEnterPressed = keyEnterPressed;
	}

	public void setKeyEscPressed(boolean keyEscPressed) {
		this.keyEscPressed = keyEscPressed;
	}

	public void setKeyLeftPressed(boolean keyLeftPressed) {
		this.keyLeftPressed = keyLeftPressed;
	}

	public void setKeyRightPressed(boolean keyRightPressed) {
		this.keyRightPressed = keyRightPressed;
	}

	public void setKeySpacePressed(boolean keySpacePressed) {
		this.keySpacePressed = keySpacePressed;
	}

	public void setKeyUpPressed(boolean keyUpPressed) {
		this.keyUpPressed = keyUpPressed;
	}

}
