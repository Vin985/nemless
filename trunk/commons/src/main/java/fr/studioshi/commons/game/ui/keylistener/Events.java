package fr.studioshi.commons.game.ui.keylistener;

public class Events {

	private static Events instance = new Events();

	public static Events getInstance() {
		return instance;
	}

	private boolean keyLeftPressed;
	private boolean keyRightPressed;
	private boolean keyUpPressed;
	private boolean keyDownPressed;

	private boolean keyAButtonPressed;
	private boolean keyBButtonPressed;

	private boolean keyEscPressed;
	private boolean keySpacePressed;
	private boolean keyEnterPressed;

	public boolean isKeyLeftPressed() {
		return keyLeftPressed;
	}

	public void setKeyLeftPressed(boolean keyLeftPressed) {
		this.keyLeftPressed = keyLeftPressed;
	}

	public boolean isKeyRightPressed() {
		return keyRightPressed;
	}

	public void setKeyRightPressed(boolean keyRightPressed) {
		this.keyRightPressed = keyRightPressed;
	}

	public boolean isKeyUpPressed() {
		return keyUpPressed;
	}

	public void setKeyUpPressed(boolean keyUpPressed) {
		this.keyUpPressed = keyUpPressed;
	}

	public boolean isKeyDownPressed() {
		return keyDownPressed;
	}

	public void setKeyDownPressed(boolean keyDownPressed) {
		this.keyDownPressed = keyDownPressed;
	}

	public boolean isKeyEscPressed() {
		return keyEscPressed;
	}

	public void setKeyEscPressed(boolean keyEscPressed) {
		this.keyEscPressed = keyEscPressed;
	}

	public boolean isKeyAButtonPressed() {
		return keyAButtonPressed;
	}

	public void setKeyAButtonPressed(boolean keyAButtonPressed) {
		this.keyAButtonPressed = keyAButtonPressed;
	}

	public boolean isKeyBButtonPressed() {
		return keyBButtonPressed;
	}

	public void setKeyBButtonPressed(boolean keyBButtonPressed) {
		this.keyBButtonPressed = keyBButtonPressed;
	}

	public boolean isKeySpacePressed() {
		return keySpacePressed;
	}

	public void setKeySpacePressed(boolean keySpacePressed) {
		this.keySpacePressed = keySpacePressed;
	}

	public boolean isKeyEnterPressed() {
		return keyEnterPressed;
	}

	public void setKeyEnterPressed(boolean keyEnterPressed) {
		this.keyEnterPressed = keyEnterPressed;
	}

	public static void setInstance(Events instance) {
		Events.instance = instance;
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

}
