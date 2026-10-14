package com.chat.models.domain;

import java.util.HashSet;
import java.util.Set;

public class GroupChat {
    private String groupId;
    private String groupName;
    private String ownerId;
    private Set<String> memberUserIds;

    public GroupChat() {
        this.memberUserIds = new HashSet<>();
    }

    public GroupChat(String groupId, String groupName, String ownerId) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.ownerId = ownerId;
        this.memberUserIds = new HashSet<>();
        this.memberUserIds.add(ownerId);
    }

    // Getters and Setters
    public String getGroupId() { return groupId; }
    public void setGroupId(String groupId) { this.groupId = groupId; }

    public String getGroupName() { return groupName; }
    public void setGroupName(String groupName) { this.groupName = groupName; }

    public String getOwnerId() { return ownerId; }
    public void setOwnerId(String ownerId) { this.ownerId = ownerId; }

    public Set<String> getMemberUserIds() { return memberUserIds; }
    public void setMemberUserIds(Set<String> memberUserIds) { this.memberUserIds = memberUserIds; }
}
