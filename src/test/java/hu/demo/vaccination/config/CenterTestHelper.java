package hu.demo.vaccination.config;

import hu.demo.vaccination.domain.Center;
import hu.demo.vaccination.dto.center.CenterCreateData;

public final class CenterTestHelper {
    public static final int ID_FIRST = 1;
    public static final String NAME_FIRST = "Test Hospital";
    public static final String CITY_FIRST = "Testville";
    public static final String EMAIL_FIRST = "test@hospital.com";
    public static final String TELEPHONE_NUMBER_FIRST = "003617654321";
    public static final int DAILY_CAPACITY_FIRST = 500;
    public static final boolean DELETED_FIRST = false;

    public static final int ID_SECOND = 2;
    public static final String NAME_SECOND = "Testpital";
    public static final String CITY_SECOND = "Tomorrowland";
    public static final String EMAIL_SECOND = "testpital@test.com";
    public static final String TELEPHONE_NUMBER_SECOND = "+3611234567";
    public static final int DAILY_CAPACITY_SECOND = 800;
    public static final boolean DELETED_SECOND = false;

    private CenterTestHelper() {
    }


    public static Center getFirstTestCenter() {
        return new Center(ID_FIRST, NAME_FIRST, CITY_FIRST, EMAIL_FIRST, TELEPHONE_NUMBER_FIRST, DAILY_CAPACITY_FIRST, DELETED_FIRST);
    }

    public static Center getSecondTestCenter() {
        return new Center(ID_SECOND, NAME_SECOND, CITY_SECOND, EMAIL_SECOND, TELEPHONE_NUMBER_SECOND, DAILY_CAPACITY_SECOND, DELETED_SECOND);
    }

    public static CenterCreateData getFirstTestCenterCreateData() {
        return new CenterCreateData(NAME_FIRST, CITY_FIRST, EMAIL_FIRST, TELEPHONE_NUMBER_FIRST, DAILY_CAPACITY_FIRST);
    }

    public static CenterCreateData getSecondTestCenterCreateData() {
        return new CenterCreateData(NAME_SECOND, CITY_SECOND, EMAIL_SECOND, TELEPHONE_NUMBER_SECOND, DAILY_CAPACITY_SECOND);
    }
}
