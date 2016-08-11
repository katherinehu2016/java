package com.oop;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Calculator");
		final JPanel panel = new JPanel();

		final JTextField inputOne = new JTextField("", 15);
		final JTextField inputTwo = new JTextField("", 15);
		final JLabel label = new JLabel();

		JButton add = new JButton("+");
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input1 = inputOne.getText();
				String input2 = inputTwo.getText();

				double value1 = Double.parseDouble(input1);
				double value2 = Double.parseDouble(input2);
				double result = value1 + value2;
				String reply = String.valueOf(result);

				label.setText("= " + reply);
				label.setVisible(true);
				panel.add(label);
			}
		});

		JButton subtract = new JButton("-");
		subtract.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input1 = inputOne.getText();
				String input2 = inputTwo.getText();

				double value1 = Double.parseDouble(input1);
				double value2 = Double.parseDouble(input2);
				double result = value1 - value2;
				String reply = String.valueOf(result);

				label.setText("= " + reply);
				label.setVisible(true);
				panel.add(label);

			}
		});

		JButton multiply = new JButton("*");
		multiply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input1 = inputOne.getText();
				String input2 = inputTwo.getText();

				double value1 = Double.parseDouble(input1);
				double value2 = Double.parseDouble(input2);
				double result = value1 * value2;
				String reply = String.valueOf(result);

				label.setText("= " + reply);
				label.setVisible(true);
				panel.add(label);
			}
		});

		JButton divide = new JButton("/");
		divide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input1 = inputOne.getText();
				String input2 = inputTwo.getText();

				double value1 = Double.parseDouble(input1);
				double value2 = Double.parseDouble(input2);
				double result = value1 / value2;
				String reply = String.valueOf(result);

				label.setText("= " + reply);
				label.setVisible(true);
				panel.add(label);
			}
		});

		frame.setSize(200, 150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(panel);

		inputOne.setVisible(true);
		inputTwo.setVisible(true);

		panel.add(inputOne);
		panel.add(add);
		panel.add(subtract);
		panel.add(multiply);
		panel.add(divide);
		panel.add(inputTwo);
	}
}
