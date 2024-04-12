package com.example.rqchallenge.employees.util;

import com.example.rqchallenge.employees.dto.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.stream.Stream;

public class MockData {


    public static AllEmployeesResponse getAllEmployeeMock() throws JsonProcessingException {
        String json = "{\"status\":\"success\",\"data\":[{\"id\":1,\"employee_name\":\"TigerNixon\",\"employee_salary\":320800,\"employee_age\":61,\"profile_image\":\"\"},{\"id\":2,\"employee_name\":\"GarrettWinters\",\"employee_salary\":170750,\"employee_age\":63,\"profile_image\":\"\"},{\"id\":3,\"employee_name\":\"AshtonCox\",\"employee_salary\":86000,\"employee_age\":66,\"profile_image\":\"\"},{\"id\":4,\"employee_name\":\"CedricKelly\",\"employee_salary\":433060,\"employee_age\":22,\"profile_image\":\"\"},{\"id\":5,\"employee_name\":\"AiriSatou\",\"employee_salary\":162700,\"employee_age\":33,\"profile_image\":\"\"},{\"id\":6,\"employee_name\":\"BrielleWilliamson\",\"employee_salary\":372000,\"employee_age\":61,\"profile_image\":\"\"},{\"id\":7,\"employee_name\":\"HerrodChandler\",\"employee_salary\":137500,\"employee_age\":59,\"profile_image\":\"\"},{\"id\":8,\"employee_name\":\"RhonaDavidson\",\"employee_salary\":327900,\"employee_age\":55,\"profile_image\":\"\"},{\"id\":9,\"employee_name\":\"ColleenHurst\",\"employee_salary\":205500,\"employee_age\":39,\"profile_image\":\"\"},{\"id\":10,\"employee_name\":\"SonyaFrost\",\"employee_salary\":103600,\"employee_age\":23,\"profile_image\":\"\"},{\"id\":11,\"employee_name\":\"JenaGaines\",\"employee_salary\":90560,\"employee_age\":30,\"profile_image\":\"\"},{\"id\":12,\"employee_name\":\"QuinnFlynn\",\"employee_salary\":342000,\"employee_age\":22,\"profile_image\":\"\"},{\"id\":13,\"employee_name\":\"ChardeMarshall\",\"employee_salary\":470600,\"employee_age\":36,\"profile_image\":\"\"},{\"id\":14,\"employee_name\":\"HaleyKennedy\",\"employee_salary\":313500,\"employee_age\":43,\"profile_image\":\"\"},{\"id\":15,\"employee_name\":\"TatyanaFitzpatrick\",\"employee_salary\":385750,\"employee_age\":19,\"profile_image\":\"\"},{\"id\":16,\"employee_name\":\"MichaelSilva\",\"employee_salary\":198500,\"employee_age\":66,\"profile_image\":\"\"},{\"id\":17,\"employee_name\":\"PaulByrd\",\"employee_salary\":725000,\"employee_age\":64,\"profile_image\":\"\"},{\"id\":18,\"employee_name\":\"GloriaLittle\",\"employee_salary\":237500,\"employee_age\":59,\"profile_image\":\"\"},{\"id\":19,\"employee_name\":\"BradleyGreer\",\"employee_salary\":132000,\"employee_age\":41,\"profile_image\":\"\"},{\"id\":20,\"employee_name\":\"DaiRios\",\"employee_salary\":217500,\"employee_age\":35,\"profile_image\":\"\"},{\"id\":21,\"employee_name\":\"JenetteCaldwell\",\"employee_salary\":345000,\"employee_age\":30,\"profile_image\":\"\"},{\"id\":22,\"employee_name\":\"YuriBerry\",\"employee_salary\":675000,\"employee_age\":40,\"profile_image\":\"\"},{\"id\":23,\"employee_name\":\"CaesarVance\",\"employee_salary\":106450,\"employee_age\":21,\"profile_image\":\"\"},{\"id\":24,\"employee_name\":\"DorisWilder\",\"employee_salary\":85600,\"employee_age\":23,\"profile_image\":\"\"}],\"message\":\"Successfully!Allrecordshasbeenfetched.\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        AllEmployeesResponse employeesResponse = objectMapper.readValue(json, new TypeReference<AllEmployeesResponse>() {});
        return employeesResponse;
    }

    public static EmployeeResponse getEmployeeById(String id) throws JsonProcessingException{
        String json = "{\"status\":\"success\",\"data\":{\"id\":1,\"employee_name\":\"TigerNixon\",\"employee_salary\":320800,\"employee_age\":61,\"profile_image\":\"\"},\"message\":\"Successfully!Recordhasbeenfetched.\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        EmployeeResponse employeeResponse = objectMapper.readValue(json, new TypeReference<EmployeeResponse>() {});
        return employeeResponse;
    }

    public static EmployeeCreateResponse CreateEmployee(EmployeeRequest employeeRequest) throws JsonProcessingException {
        String json = "{\"status\":\"success\",\"data\":{\"name\":\"test\",\"salary\":\"123\",\"age\":\"23\",\"id\":25}}";
        ObjectMapper mapper = new ObjectMapper();
        EmployeeCreateResponse employeeCreateResponse = mapper.readValue(json, new TypeReference<EmployeeCreateResponse>() {});
        return employeeCreateResponse;
    }

    public static BaseResponse deleteEmployById(String id) throws JsonProcessingException {
        String json = "{\"status\":\"success\",\"message\":\"successfully!deletedRecords\"}";
        ObjectMapper mapper = new ObjectMapper();
        BaseResponse baseResponse = mapper.readValue(json, new TypeReference<BaseResponse>() {});
        return baseResponse;
    }

}
