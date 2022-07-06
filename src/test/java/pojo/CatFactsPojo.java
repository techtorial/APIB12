package pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class CatFactsPojo {

    private int current_page;
    private List<Map<String, Object>> data;
    private List<Map<String, Object>> links;
    private String first_page_url;
    private String last_page_url;
    private String next_page_url;
    private String prev_page_url;
    private String path;
    private int from;
    private int last_page;
    private int to;
    private int total;
    private int per_page;

}
