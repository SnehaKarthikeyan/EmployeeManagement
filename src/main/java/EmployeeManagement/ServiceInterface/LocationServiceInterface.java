package com.inzeph.EmployeeManagement.ServiceInterface;

import com.inzeph.EmployeeManagement.Model.Location;

public interface LocationServiceInterface {
    Location addLocation(Location location);
    Location getByEmpId(Long empid);
}
