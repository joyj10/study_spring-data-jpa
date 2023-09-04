package study.datajpa.dto;

import lombok.Data;

/**
 * MemberDto
 * <pre>
 * Describe here
 * </pre>
 *
 * @version 1.0,
 */
@Data
public class MemberDto {
    private Long id;
    private String username;
    private String teamName;

    public MemberDto(Long id, String username, String teamName) {
        this.id = id;
        this.username = username;
        this.teamName = teamName;
    }
}
