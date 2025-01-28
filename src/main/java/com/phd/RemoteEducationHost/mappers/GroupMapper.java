package com.phd.RemoteEducationHost.mappers;

import com.phd.RemoteEducationHost.enteties.Group;
import com.phd.RemoteEducationHost.enteties.Specialty;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class GroupMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
//        System.out.println(rs.getMetaData().getColumnCount());
        Group group = new Group();
        group.setId(rs.getInt("group_id"));
        group.setName(rs.getString("group_name"));
        group.setCreationDate(rs.getDate("creation_date"));
        Specialty specialty = new Specialty(rs.getInt("specialty"));
        group.setSpecialty(specialty);
        if(rs.getMetaData().getColumnCount() > 4) {
            specialty.setName(rs.getString("specialty_name"));
            specialty.setDepartment(rs.getString("department_name"));
        }
        return group;
    }
//    public static Group groupDTOtoGroup(){
//        return new Group();
//    }
}
