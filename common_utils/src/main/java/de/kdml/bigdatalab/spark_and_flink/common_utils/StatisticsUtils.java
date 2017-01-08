package de.kdml.bigdatalab.spark_and_flink.common_utils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import de.kdml.bigdatalab.spark_and_flink.common_utils.data.Trajectory;
import de.kdml.bigdatalab.spark_and_flink.common_utils.data.TrajectoryStatisticsWrapper;

/**
 * 
 * 
 * @author Ehab Qadah
 * 
 *         Jan 3, 2017
 */
public class StatisticsUtils {

	/**
	 * 
	 * @param trajectories
	 * @return
	 */
	public static List<Trajectory> computeStatistics(List<Trajectory> trajectories) {

		double minLongtitude = Double.MAX_VALUE, minLatitude = Double.MAX_VALUE, minAltitude = Double.MAX_VALUE,
				minSpeed = Double.MAX_VALUE, minAcceleration = Double.MAX_VALUE;
		double maxLongtitude = Double.MIN_VALUE, maxLatitude = Double.MIN_VALUE, maxAltitude = Double.MIN_VALUE,
				maxSpeed = Double.MIN_VALUE, maxAcceleration = Double.MIN_VALUE;

		Trajectory prevTrajectory = null;
		for (Trajectory trajectory : trajectories) {

			TrajectoriesUtils.calculateDistanceAndSpeedOfTrajectory(prevTrajectory, trajectory);

			double longtitude = trajectory.getLongitude(), lat = trajectory.getLatitude(),
					altit = trajectory.getAltitude(), speed = trajectory.getSpeed(),
					acceleration = trajectory.getAcceleration();
			// update min longitude
			minLongtitude = Math.min(minLongtitude, longtitude);
			// update min latitude
			minLatitude = Math.min(minLatitude, lat);
			minAltitude = Math.min(minAltitude, altit);
			minAcceleration = Math.min(minAcceleration, acceleration);
			minSpeed = Math.min(minSpeed, speed);
			// update max longitude
			maxLongtitude = Math.max(maxLongtitude, longtitude);
			// update max latitude
			maxLatitude = Math.max(maxLatitude, lat);
			maxAltitude = Math.max(maxAltitude, altit);
			maxSpeed = Math.max(maxSpeed, speed);
			maxAcceleration = Math.max(maxAcceleration, acceleration);

			prevTrajectory = trajectory;

		}

		TrajectoryStatisticsWrapper statistics = new TrajectoryStatisticsWrapper();
		statistics.setMinLong(minLongtitude);
		statistics.setMinLat(minLatitude);
		statistics.setMinAltitude(minAltitude);
		statistics.setMaxLong(maxLongtitude);
		statistics.setMaxLat(maxLatitude);
		statistics.setMaxAltitude(maxAltitude);
		statistics.setMaxAcceleration(maxAcceleration);
		statistics.setMinAcceleration(minAcceleration);
		statistics.setMaxSpeed(maxSpeed);
		statistics.setMinSpeed(minSpeed);

		for (Trajectory trajectory : trajectories) {

			// just update new trajectories
			if (trajectory.getStatistics() == null) {

				trajectory.setStatistics(statistics);
			}

		}

		return trajectories;
	}

}
