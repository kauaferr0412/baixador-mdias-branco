package com.baixador.mdias;

import javax.swing.*;

public class MdiasApplication {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Aplicacao().setVisible(true);
			}
		});
	}

}
