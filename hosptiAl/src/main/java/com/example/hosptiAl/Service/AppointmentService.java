package com.example.hosptiAl.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hosptiAl.Model.Appointment;
import com.example.hosptiAl.Model.Appointment.Status;
import com.example.hosptiAl.Repository.AppointmentRep;
import com.example.hosptiAl.Repository.DocRe;

@Service
public class AppointmentService {

	
	@Autowired
	private AppointmentRep appointmentRep;
	@Autowired
	private DocRe docRe;

	public void scheduleAppointment(Appointment appointment) {
		appointment.setStatus(Status.CONFIRMED);
		appointmentRep.save(appointment);
	}
	
	public Appointment getAppointmentDetails(long appointmentId){
		return appointmentRep.findById(appointmentId).orElse(new Appointment());
	}
	
	public void cancelAppointment(long appointmentId){
		appointmentRep.deleteById(appointmentId);
	}
	public void updateAppointment(long appointmentId){
		Appointment appointment=appointmentRep.findById(appointmentId).orElse(new Appointment());
		appointment.setStatus(Status.CANCELLED);
		appointmentRep.save(appointment);
	}
	
	public List<Object[]> allDetails(){
		return appointmentRep.findAppointmentsWithPatientAndDoctorDetails();
	}
	public Iterable<String> getBytime(long id){
		
		return appointmentRep.findTimeSlotsByDoctorId(id);
	}
	 public List<String> getBookedTimeSlots( long doctorId,  String appointmentDate) {
			List<String> timeSlot = new ArrayList<>(Arrays.asList(
		            "09:00 AM - 09:30 AM",
		            "09:30 AM - 10:00 AM",
		            "10:00 AM - 10:30 AM",
		            "10:30 AM - 11:00 AM",
		            "11:00 AM - 11:30 AM",
		            "11:30 AM - 12:00 PM",
		            "12:00 PM - 12:30 PM",
		            "12:30 PM - 01:00 PM",
		            "01:00 PM - 01:30 PM",
		            "01:30 PM - 02:00 PM",
		            "02:00 PM - 02:30 PM",
		            "02:30 PM - 03:00 PM",
		            "03:00 PM - 03:30 PM",
		            "03:30 PM - 04:00 PM",
		            "04:00 PM - 04:30 PM",
		            "04:30 PM - 05:00 PM",
		            "05:00 PM - 05:30 PM",
		            "05:30 PM - 06:00 PM"
		        ));
	        LocalDate date = LocalDate.parse(appointmentDate);
	        String time=docRe.findById(doctorId).get().getAvailabilitySchedule();
	        String doctimst=time.substring(0,7),doctimen=time.substring(time.length()-6,time.length());
	        System.out.println(doctimst +"   " +doctimen);
	        List<Appointment> bookedAppointments = appointmentRep.findByDoctorDoctorIdAndAppointmentDateAndStatus(doctorId, date,Status.CONFIRMED);
	        List<String> ans= bookedAppointments.stream()
	            .map(Appointment::getTimeSlot)
	            .collect(Collectors.toList());
	        List<String> availableSlots =removeTime(doctimst,doctimen);
	        System.out.println("list remove:"+availableSlots );
//	        for(int i=0;i<ans.size();i++) {
//	        	if(remans.contains(ans.get(i))) remans.remove(ans.get(i));
//	        }
	        availableSlots.removeAll(ans);
//	        System.out.println("filtered"+remans);
//	        System.out.println("after removed"+ans);
	        
	        return availableSlots;
	    }
	 
	 public static int parseTime(String time) {
	        time = time.replace(" ", "");
	        int hour = Integer.parseInt(time.substring(0, time.indexOf(':')));
	        int minute = Integer.parseInt(time.substring(time.indexOf(':') + 1, time.indexOf('A') != -1 ? time.indexOf('A') : time.indexOf('P')));
	        boolean isPM = time.contains("PM");
	        if (hour == 12) hour = 0;
	        int total = hour * 60 + minute;
	        if (isPM) total += 12 * 60;
	        return total;
	    }

	    public static List<String> filterTimeSlots(List<String> timeSlots, String start, String end) {
	        int filterStart = parseTime(start);
	        int filterEnd = parseTime(end);

	        return timeSlots.stream()
	            .filter(slot -> {
	                String[] parts = slot.split(" - ");
	                int slotStart = parseTime(parts[0]);
	                int slotEnd = parseTime(parts[1]);
	                return slotStart >= filterStart && slotEnd <= filterEnd;
	            })
	            .collect(Collectors.toList());
	    }

	    List<String> removeTime(String start,String end) {
	        List<String> timeSlot = new ArrayList<>(Arrays.asList(
	            "09:00 AM - 09:30 AM",
	            "09:30 AM - 10:00 AM",
	            "10:00 AM - 10:30 AM",
	            "10:30 AM - 11:00 AM",
	            "11:00 AM - 11:30 AM",
	            "11:30 AM - 12:00 PM",
	            "12:00 PM - 12:30 PM",
	            "12:30 PM - 01:00 PM",
	            "01:00 PM - 01:30 PM",
	            "01:30 PM - 02:00 PM",
	            "02:00 PM - 02:30 PM",
	            "02:30 PM - 03:00 PM",
	            "03:00 PM - 03:30 PM",
	            "03:30 PM - 04:00 PM",
	            "04:00 PM - 04:30 PM",
	            "04:30 PM - 05:00 PM",
	            "05:00 PM - 05:30 PM",
	            "05:30 PM - 06:00 PM"
	        ));

	        

	        List<String> filteredSlots = filterTimeSlots(timeSlot, start, end);

	        System.out.println("Filtered Time Slots:");
	        filteredSlots.forEach(System.out::println);
	        return filteredSlots;
	    }

		public Iterable<Object[]> getdoctorappbyToday(String userName) {
			long doctorId=docRe.findByName(userName).getDoctorId();
			return appointmentRep.findTodayAppointmentsWithDetailsByDoctorId(doctorId);
		}

//	
}
