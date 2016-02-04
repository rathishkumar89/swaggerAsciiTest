package com.parse.asciidoc.ascii_to_pdf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestFile {
	public static void main(String[] args) {
		new TestFile().doSom();
	}

	private void doSom() {
		String[] abc = { "1#3", "2#3", "5#7", "3#4" };
		List<Booking> bookings = new ArrayList<Booking>();
		List<Booking> fbookings = new ArrayList<Booking>();
		int count = 0;

		for (String entry : abc) {
			bookings.add(new Booking(entry));
		} 
		
		for (Booking booking : bookings) {
			if (isContains(booking, bookings)) {
				fbookings.add(booking);
			}
		}

		bookings.removeAll(fbookings);
		fbookings = new ArrayList<TestFile.Booking>();
		fbookings = findIsolated(bookings);
		count = fbookings.size();
		bookings.removeAll(fbookings);

		Collections.sort(bookings, new Comparator<Booking>() {
			public int compare(Booking o1, Booking o2) {
				return Integer.compare(o1.getsT(), o2.getsT());
			}
		});

		while (!bookings.isEmpty()) {
			Booking booking = bookings.get(0);
			removeLaying(booking, bookings);
			count++;
		}
		System.out.println(count);
	}

	private void removeLaying(Booking booking, List<Booking> bookings) {
		List<Booking> tempList = new ArrayList<TestFile.Booking>();

		for (Booking item : bookings) {
			if (item != booking && booking.getsT() < item.getsT()
					&& booking.geteT() < item.geteT()
					&& booking.geteT() > item.getsT()) {
				tempList.add(item);
			}
		}
		tempList.add(booking);
		bookings.removeAll(tempList);
	}

	private boolean isContains(Booking booking, List<Booking> bookings) {
		for (Booking item : bookings) {
			if (item != booking && booking.getsT() <= item.getsT()
					&& booking.geteT() >= item.geteT()) {
				return true;
			}
		}
		return false;
	}

	private List<Booking> findIsolated(List<Booking> bookings) {
		List<Booking> isolatedBlocks = new ArrayList<TestFile.Booking>();
		return isolatedBlocks;
	}

	class Booking {
		private int sT;
		private int eT;
		private int diff;

		public int getDiff() {
			return diff;
		}

		public void setDiff(int diff) {
			this.diff = diff;
		}

		public Booking(String Dur) {
			String[] timeDiv = Dur.split("#");
			sT = Integer.parseInt(timeDiv[0]);
			eT = Integer.parseInt(timeDiv[1]);
			if (eT < sT) {
				int k = eT;
				eT = sT;
				sT = k;
			}
			diff = eT - sT;

		}

		public int getsT() {
			return sT;
		}

		public void setsT(int sT) {
			this.sT = sT;
		}

		public int geteT() {
			return eT;
		}

		public void seteT(int eT) {
			this.eT = eT;
		}

		public String toString() {
			return "[" + sT + "," + eT + "]";
		}
	}
}
