package com.zyl.hospital.registration.http.api;


import com.zyl.hospital.registration.bean.Appointment;
import com.zyl.hospital.registration.bean.Department;
import com.zyl.hospital.registration.bean.DoctorBean;
import com.zyl.hospital.registration.bean.DoctorSchedule;
import com.zyl.hospital.registration.bean.Hospital;
import com.zyl.hospital.registration.bean.PatientBean;
import com.zyl.hospital.registration.bean.ResultEntity;
import com.zyl.hospital.registration.constants.ApiConstant;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiService {
    //--------------医院数据请求
    @GET(ApiConstant.getHospitals)
    Observable<ResultEntity<List<Hospital>>> getHospitals();

    //--------------部门数据请求
    @GET(ApiConstant.getdepartmentsbyhid)
    Observable<ResultEntity<List<Department>>> getDepartsments(
            @Query("hospitalId") String hospitalId,
            @Query("page") int page,
            @Query("size") int size);


    //--------------医生数据请求
    @POST(ApiConstant.doctorlogin)
    @FormUrlEncoded
    Observable<ResultEntity<DoctorBean>> doctorlogin(
            @Field("account") String account,
            @Field("pwd") String password);


    //--------------病人数据请求
    @POST(ApiConstant.patientlogin)
    @FormUrlEncoded
    Observable<ResultEntity<PatientBean>> patientlogin(
            @Field("account") String account,
            @Field("pwd") String password);

    @POST(ApiConstant.patientregister)
    @FormUrlEncoded
    Observable<ResultEntity<PatientBean>> patientregister(
            @Field("account") String account,
            @Field("pwd") String password);

    @POST(ApiConstant.modifypatientinfo)
    @FormUrlEncoded
    Observable<ResultEntity<PatientBean>> modifyPatientInfo(
            @Field(value = "account") String account,
            @Field(value = "oldPwd") String oldPwd,
            @Field(value = "newPwd") String newPwd,
            @Field(value = "realName") String realName,
            @Field(value = "sex") int sex,
            @Field(value = "birthDay") long birthDay,
            @Field(value = "portraint") String portraint,
            @Field(value = "mobilePhone") String mobilePhone);


    //--------------医生日程数据请求
    ///schedule/queryAllScheduleByDoctorAccount
    @GET(ApiConstant.getdoctorschedules)
    @FormUrlEncoded
    Observable<ResultEntity<List<DoctorSchedule>>> queryAllScheduleByDoctorAccount(
            @Query("doctorAccount") String doctorAccount);

    //添加日程
    @GET(ApiConstant.addschedule)
    @FormUrlEncoded
    Observable<ResultEntity<DoctorSchedule>> addSchedule(@Query("doctorAccount") String doctorAccount,
                                                         @Query("status") int status,
                                                         @Query("maxAppointmentCount") int maxAppointmentCount,
                                                         @Query("scheduleDate") long scheduleDate);

    //--------------预约数据请求
    @GET(ApiConstant.makeappointment)
    @FormUrlEncoded
    Observable<ResultEntity<?>> makeAppointment(@Query(value = "patientId") String patientId,
                                                @Query(value = "doctorId") String doctorId,
                                                @Query(value = "doctorScheduleId") String doctorScheduleId,
                                                @Query(value = "price") float price,
                                                @Query(value = "clinicDate") long clinicDate,
                                                @Query(value = "appointDate") long appointDate,
                                                @Query(value = "location") String location);
    @GET(ApiConstant.querydetail)
    @FormUrlEncoded
    Observable<ResultEntity<Appointment>> queryDetailInfo(@Query(value = "doctorScheduleId") String doctorScheduleId);


    @GET(ApiConstant.queryAllAppointmentByPid)
    @FormUrlEncoded
    Observable<ResultEntity<List<Appointment>>> queryAllAppointmentByPid(@Query(value = "patientId") String patientId,
                                                                         @Query(value = "page") int page,
                                                                         @Query(value = "size") int size);
    @GET(ApiConstant.queryAllByDid)
    @FormUrlEncoded
    Observable<ResultEntity<List<Appointment>>> queryAllAppointmentByDid(@Query(value = "doctorId") String doctorId,
                                                                         @Query(value = "page") int page,
                                                                         @Query(value = "size") int size);
    @GET(ApiConstant.queryAllByDidAndStatus)
    @FormUrlEncoded
    Observable<ResultEntity<List<Appointment>>> queryByDoctorIdAndStatus(@Query(value = "doctorId") String doctorId,
                                                                         @Query(value = "status") int status,
                                                                         @Query(value = "page") int page,
                                                                         @Query(value = "size") int size);
    @GET(ApiConstant.queryAllAppointmentByPid)
    @FormUrlEncoded
    Observable<ResultEntity<List<Appointment>>> queryByPatientIdAndStatus(@Query(value = "patientId") String patientId,
                                                                          @Query(value = "status") int status,
                                                                          @Query(value = "page") int page,
                                                                          @Query(value = "size") int size);
    //queryByPatientId

}
