package com.fintech.config;

public class weww {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(ConfigManager.getInstance().getProperty("env"));
		System.out.println(ConfigManager.getInstance().getBaseUrl());
	}

}
