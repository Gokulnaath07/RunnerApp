package dev.gokul.runnerz.run;
import jakarta.annotation.PostConstruct;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
public class RunRepository {

    private static final Logger log = LoggerFactory.getLogger(RunRepository.class);
    private JdbcClient jdbcClient;

    public RunRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @PostConstruct
    public void init() {
        createTableIfNotExists();
    }

    // Create table if it doesn't exist
    private void createTableIfNotExists() {
        jdbcClient.sql("CREATE TABLE IF NOT EXISTS run (" +
                "id INT PRIMARY KEY, " +
                "title VARCHAR(255), " +
                "started_on TIMESTAMP, " +
                "completed_on TIMESTAMP, " +
                "miles DOUBLE, " +
                "location VARCHAR(50))"
        ).update();
    }

    // Get all runs
    public List<Run> findAll() {
        return jdbcClient.sql("SELECT * FROM run")
                .query(Run.class)
                .list();
    }

    // Find by id
    public Optional<Run> findById(Integer id) {
        return jdbcClient.sql("SELECT id, title, started_on, completed_on, miles, location FROM run WHERE id = :id")
                .param("id", id)
                .query(Run.class)
                .optional();
    }

    // Create a new run
    public void create(Run run) {
        var updated = jdbcClient.sql("INSERT INTO run (id, title, started_on, completed_on, miles, location) VALUES (?, ?, ?, ?, ?, ?)")
                .params(List.of(run.id(), run.title(), run.startedOn(), run.completedOn(), run.miles(), run.location().toString()))
                .update();
        Assert.state(updated == 1, "Failed to create run " + run.title());
    }

    // Update an existing run
    public void update(Run run, Integer id) {
        var updated = jdbcClient.sql("UPDATE run SET title = ?, started_on = ?, completed_on = ?, miles = ?, location = ? WHERE id = ?")
                .params(List.of(run.title(), run.startedOn(), run.completedOn(), run.miles(), run.location().toString(), id))
                .update();
        Assert.state(updated == 1, "Failed to update run " + run.title());
    }

    // Delete a run
    public void delete(Integer id) {
        var updated = jdbcClient.sql("DELETE FROM run WHERE id = :id").param("id", id).update();
        Assert.state(updated == 1, "Failed to delete run with id " + id);
    }

    // Get the number of rows
    public int count() {
        return jdbcClient.sql("SELECT * FROM run").query().listOfRows().size();
    }

    // Save all runs
    public void saveAll(List<Run> runs) {
        runs.forEach(this::create);
    }

    // Find runs by location
    public List<Run> findByLocation(Location location) {
        return jdbcClient.sql("SELECT * FROM run WHERE location = :location")
                .param("location", location.toString())
                .query(Run.class)
                .list();
    }
}
