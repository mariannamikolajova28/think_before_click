package sk.tuke.thinkbeforeclick;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class DebugController {

    private final JdbcTemplate jdbcTemplate;

    public DebugController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/debug/all")
    public Map<String, List<Map<String, Object>>> getAllTables() {
        Map<String, List<Map<String, Object>>> result = new HashMap<>();

        // získa všetky tabuľky v H2
        List<String> tables = jdbcTemplate.queryForList(
                "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA='PUBLIC'",
                String.class
        );

        for (String table : tables) {
            try {
                List<Map<String, Object>> rows =
                        jdbcTemplate.queryForList("SELECT * FROM " + table);
                result.put(table, rows);
            } catch (Exception e) {
                result.put(table, List.of(Map.of("error", e.getMessage())));
            }
        }

        return result;
    }
}
