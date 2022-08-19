package com.techelevator.projects.dao;

import com.techelevator.projects.model.Timesheet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

public class JdbcTimesheetDaoTests extends BaseDaoTests {

    private static final Timesheet TIMESHEET_1 = new Timesheet(1, 1, 1,
            LocalDate.parse("2021-01-01"), 1.0, true, "Timesheet 1");
    private static final Timesheet TIMESHEET_2 = new Timesheet(2, 1, 1,
            LocalDate.parse("2021-01-02"), 1.5, true, "Timesheet 2");
    private static final Timesheet TIMESHEET_3 = new Timesheet(3, 2, 1,
            LocalDate.parse("2021-01-01"), 0.25, true, "Timesheet 3");
    private static final Timesheet TIMESHEET_4 = new Timesheet(4, 2, 2,
            LocalDate.parse("2021-02-01"), 2.0, false, "Timesheet 4");
    
    private JdbcTimesheetDao sut;


    @Before
    public void setup() {
        sut = new JdbcTimesheetDao(dataSource);
    }

    @Test
    public void getTimesheet_returns_correct_timesheet_for_id() {
        Timesheet expectedTimesheet = sut.getTimesheet(TIMESHEET_1.getTimesheetId());
        assertTimesheetsMatch(TIMESHEET_1, expectedTimesheet);

        expectedTimesheet = sut.getTimesheet(2);
        assertTimesheetsMatch(TIMESHEET_2,expectedTimesheet);

        expectedTimesheet = sut.getTimesheet(4);
        assertTimesheetsMatch(TIMESHEET_4,expectedTimesheet);
    }

    @Test
    public void getTimesheet_returns_null_when_id_not_found() {
        Timesheet timesheet = sut.getTimesheet(99);
        Assert.assertEquals(null,timesheet);

        timesheet = sut.getTimesheet(-1);
        Assert.assertEquals(null,timesheet);
    }

    @Test
    public void getTimesheetsByEmployeeId_returns_list_of_all_timesheets_for_employee() {
        List<Timesheet> timeSheets = sut.getTimesheetsByEmployeeId(1);
        Assert.assertEquals(2, timeSheets.size());
        assertTimesheetsMatch(TIMESHEET_1, timeSheets.get(0));
        assertTimesheetsMatch(TIMESHEET_2, timeSheets.get(1));

        Timesheet timesheet = new Timesheet(99,2,1,LocalDate.now(), 1,true,"");
        timeSheets = sut.getTimesheetsByEmployeeId(2);
        Assert.assertEquals(2, timeSheets.size());
    }

    @Test
    public void getTimesheetsByProjectId_returns_list_of_all_timesheets_for_project() {
        List<Timesheet> timeSheets = sut.getTimesheetsByProjectId(1);
        Assert.assertEquals(3, timeSheets.size());
        assertTimesheetsMatch(TIMESHEET_1, timeSheets.get(0));
        assertTimesheetsMatch(TIMESHEET_2, timeSheets.get(1));
        assertTimesheetsMatch(TIMESHEET_3, timeSheets.get(2));


    }

    @Test
    public void createTimesheet_returns_timesheet_with_id_and_expected_values() {
        Timesheet expectedTimesheet = new Timesheet(4, 2, 1, LocalDate.parse("2021-02-01"), 2.0, true, "Timesheet 8");
        Timesheet actual = sut.createTimesheet(expectedTimesheet);
        expectedTimesheet.setTimesheetId(actual.getTimesheetId());

        assertTimesheetsMatch(expectedTimesheet,actual);

    }
    @Test
    public void created_timesheet_has_expected_values_when_retrieved() {
        Timesheet expectedTimesheet = new Timesheet(4, 2, 1, LocalDate.parse("2021-02-01"), 1.0, true, "Timesheet 7");
        Timesheet createdTimesheet = sut.createTimesheet(expectedTimesheet);
        Timesheet actualTimesheet = sut.getTimesheet(createdTimesheet.getTimesheetId());

        assertTimesheetsMatch(createdTimesheet,actualTimesheet);

    }

    @Test
    public void updated_timesheet_has_expected_values_when_retrieved() {
        Timesheet updatedTimesheet = sut.getTimesheet(1);
        updatedTimesheet.setEmployeeId(2);
        updatedTimesheet.setProjectId(1);
        updatedTimesheet.setDateWorked(LocalDate.now());
        updatedTimesheet.setHoursWorked(6.0);
        updatedTimesheet.setBillable(true);
        updatedTimesheet.setDescription("Updated Timesheet");
        sut.updateTimesheet(updatedTimesheet);
        Timesheet retrievedTimesheet = sut.getTimesheet(1);

        assertTimesheetsMatch(updatedTimesheet, retrievedTimesheet);

    }

    @Test
    public void deleted_timesheet_cant_be_retrieved() {
        sut.deleteTimesheet(1);
        Timesheet retrievedTimesheet = sut.getTimesheet(1);
        Assert.assertNull(retrievedTimesheet);
    }

    @Test
    public void getBillableHours_returns_correct_total() {
        double billableHours = sut.getBillableHours(1,1);
        double nonbillableHours = sut.getBillableHours(2,2);
        Assert.assertEquals(2.5,billableHours, 0.1);
        Assert.assertEquals(0, nonbillableHours, 0.1);
    }
    private void assertTimesheetsMatch(Timesheet expected, Timesheet actual) {
        Assert.assertEquals(expected.getTimesheetId(), actual.getTimesheetId());
        Assert.assertEquals(expected.getEmployeeId(), actual.getEmployeeId());
        Assert.assertEquals(expected.getProjectId(), actual.getProjectId());
        Assert.assertEquals(expected.getDateWorked(), actual.getDateWorked());
        Assert.assertEquals(expected.getHoursWorked(), actual.getHoursWorked(), 0.001);
        Assert.assertEquals(expected.isBillable(), actual.isBillable());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
    }

}
