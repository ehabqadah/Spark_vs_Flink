package de.kdml.bigdatalab.spark_and_flink.common_utils.data;

import java.io.Serializable;

import de.kdml.bigdatalab.spark_and_flink.common_utils.Utils;

public class Trajectory implements Serializable {

	private static final long serialVersionUID = 1013765199466780042L;
	private String ID;
	private double latitude;
	private double longtitude;
	private double altitude;
	private String type;

	public Trajectory() {
	}

	public Trajectory(String id, String type, double latitude, double longtitude, double altitude) {

		this.ID = id;
		this.setLatitude(latitude);
		this.setLongtitude(longtitude);
		this.setAltitude(altitude);
		this.setType(type);

	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(double longtitude) {
		this.longtitude = longtitude;
	}

	public double getAltitude() {
		return altitude;
	}

	public void setAltitude(double altitude2) {
		this.altitude = altitude2;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {

		return "Type:ID" + this.getType() + ":" + getID() + " (lat,long,alt):(" + getLatitude() + "," + getLongtitude()
				+ "," + getAltitude() + ")\n";
	}

	/**
	 * Generate a trajectory from input data string in format of
	 * (MSG,3,,,34324E,,2015/08/03,01:05:03.844,2015/08/03,01:05:07.058,,30050,,,45.69032,5.54741,,,0,0,0,0)
	 * 
	 * @param line
	 * @return
	 */
	public static Trajectory parseDataInput(String line) {

		String[] attributes = line.split(",");

		Trajectory trajectory = new Trajectory();
		if (attributes.length >5) {
			trajectory.setType(attributes[0] + attributes[1]);
			trajectory.setID(attributes[4]);

		}

		if (attributes.length > 11) {
			// get location coordinates
			trajectory.setAltitude(Utils.parseDoubleOrElse(attributes[11], 0.0));
			trajectory.setLongtitude(Utils.parseDoubleOrElse(attributes[15], 0.0));
			trajectory.setLatitude(Utils.parseDoubleOrElse(attributes[14], 0.0));
		}
		return trajectory;

	}

}