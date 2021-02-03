package quanlithuvien.controller;

import org.hibernate.Session;

import quanlithuvien.datasoucre.HibernateConfig;

public class main {
	private static Session session;
	public static void main(String[] args) {
		session = HibernateConfig.buildSessionFactory().openSession();
	}
}
