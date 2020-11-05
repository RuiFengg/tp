package seedu.homerce.logic.commands.client;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.homerce.commons.core.GuiSettings;
import seedu.homerce.logic.commands.CommandResult;
import seedu.homerce.logic.commands.exceptions.CommandException;
import seedu.homerce.model.Model;
import seedu.homerce.model.ReadOnlyUserPrefs;
import seedu.homerce.model.appointment.Appointment;
import seedu.homerce.model.client.Client;
import seedu.homerce.model.client.Phone;
import seedu.homerce.model.expense.Expense;
import seedu.homerce.model.manager.ClientManager;
import seedu.homerce.model.manager.HistoryManager;
import seedu.homerce.model.manager.ReadOnlyAppointmentManager;
import seedu.homerce.model.manager.ReadOnlyClientManager;
import seedu.homerce.model.manager.ReadOnlyExpenseTracker;
import seedu.homerce.model.manager.ReadOnlyRevenueTracker;
import seedu.homerce.model.manager.ReadOnlyServiceManager;
import seedu.homerce.model.revenue.Revenue;
import seedu.homerce.model.service.Service;
import seedu.homerce.model.service.ServiceCode;
import seedu.homerce.testutil.client.ClientBuilder;

public class AddClientCommandTest {

    @Test
    public void constructor_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddClientCommand(null));
    }

    @Test
    public void execute_clientAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingClientAdded modelStub = new ModelStubAcceptingClientAdded();
        Client validClient = new ClientBuilder().build();

        CommandResult commandResult = new AddClientCommand(validClient).execute(modelStub,
            HistoryManager.getInstance());

        assertEquals(String.format(AddClientCommand.MESSAGE_SUCCESS, validClient), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validClient), modelStub.clientsAdded);
    }

    @Test
    public void execute_duplicateClient_throwsCommandException() {
        Client validClient = new ClientBuilder().build();
        AddClientCommand addClientCommand = new AddClientCommand(validClient);
        ModelStub modelStub = new ModelStubWithClient(validClient);

        assertThrows(CommandException.class, AddClientCommand.MESSAGE_DUPLICATE_CLIENT, () ->
            addClientCommand.execute(modelStub, HistoryManager.getInstance()));
    }

    @Test
    public void equals() {
        Client alice = new ClientBuilder().withName("Alice").build();
        Client bob = new ClientBuilder().withName("Bob").build();
        AddClientCommand addAliceCommand = new AddClientCommand(alice);
        AddClientCommand addBobCommand = new AddClientCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddClientCommand addAliceCommandCopy = new AddClientCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different client -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    public static class ModelStub implements Model {

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getClientManagerFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setClientManagerFilePath(Path clientManagerFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addClient(Client client) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addExpense(Expense expense) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setClientManager(ReadOnlyClientManager newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyClientManager getClientManager() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasClient(Client client) {
            throw new AssertionError("This method should not be called.");
        }

        public boolean checkClientWithPhone(Phone phone) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteClient(Client target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setClient(Client target, Client editedClient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Client> getFilteredClientList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredClientList(Predicate<Client> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        // ============================== Expense Tracker ====================================

        @Override
        public void deleteExpense(Expense target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setExpense(Expense target, Expense editedExpense) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setExpenses(List<Expense> expenses) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Expense> getFilteredExpenseList() {
            throw new AssertionError("This method should not be called.");
        }


        @Override
        public void updateFilteredExpenseList(Predicate<Expense> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyExpenseTracker getExpenseTracker() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setExpenseTracker(ReadOnlyExpenseTracker expenseTracker) {
            throw new AssertionError("This method should not be called.");
        }


        // ============================== Service Management ====================================

        @Override
        public void addService(Service toAdd) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyServiceManager getServiceManager() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setServiceManager(ReadOnlyServiceManager serviceManager) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Service> getFilteredServiceList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredServiceList(Predicate<Service> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        // ============================== Revenue Tracker ====================================

        @Override
        public void addRevenue(Revenue toAdd) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteRevenue(Revenue target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setRevenues(List<Revenue> revenues) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredRevenueList(Predicate<Revenue> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Revenue> getFilteredRevenueList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyRevenueTracker getRevenueTracker() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setRevenueTracker(ReadOnlyRevenueTracker revenueTracker) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteService(Service target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setService(Service target, Service editedService) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Client getClientByPhone(Phone phone) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasService(ServiceCode code) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Service getServiceByServiceCode(ServiceCode serviceCode) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAppointment(Appointment toAdd) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteAppointment(Appointment target) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void updateFilteredAppointmentList(Predicate<Appointment> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Appointment> getFilteredAppointmentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAppointmentManager getAppointmentManager() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAppointment(Appointment target, Appointment editedAppointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAppointment(List<Appointment> appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAppointmentManager(ReadOnlyAppointmentManager appointmentManager) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Appointment> getFilteredSchedule() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void nextSchedulePage() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void previousSchedulePage() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void refreshSchedule() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredSchedule(Predicate<Appointment> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAppointmentManagerCalendar(Calendar calendar) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void replaceModel(Model previousModel) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Model deepCopy() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single client.
     */
    private class ModelStubWithClient extends ModelStub {

        private final Client client;

        ModelStubWithClient(Client client) {
            requireNonNull(client);
            this.client = client;
        }

        @Override
        public boolean hasClient(Client client) {
            requireNonNull(client);
            return this.client.isSameClient(client);
        }
    }

    /**
     * A Model stub that always accept the client being added.
     */
    private class ModelStubAcceptingClientAdded extends ModelStub {

        final ArrayList<Client> clientsAdded = new ArrayList<>();

        @Override
        public boolean hasClient(Client client) {
            requireNonNull(client);
            return clientsAdded.stream().anyMatch(client::isSameClient);
        }

        @Override
        public void addClient(Client client) {
            requireNonNull(client);
            clientsAdded.add(client);
        }

        @Override
        public ReadOnlyClientManager getClientManager() {
            return new ClientManager();
        }
    }

}
