package net.yorksolutions.emilymilldrumcapstonebe.stage;

import net.yorksolutions.emilymilldrumcapstonebe.stageOptions.StageOptions;

import java.util.List;

public class StageAddDTO {
    public String question;
    public Integer stageOrder;
    public String type;
    public List<StageOptions> stageOptions;
    public Integer processId;
}
