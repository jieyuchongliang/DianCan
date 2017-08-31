package com.fujisoft.diancan.bean;

import java.util.List;

/**
 * Created by 860617010 on 2017/8/25.
 */

public class TaskHallBean {

    /**
     * success : true
     * msg : 请求成功
     * data : {"requiredTask":null,"optionalTask":[{"id":"321","name":"京津冀国土去爷爷家","subtitle":null,"picUrl":"/Uploads/task/58c79d9521680.jpeg","content":"路","minShareTime":null,"maxShareTime":"5","completeFlag":"0","requiredFlags":"0","staff_flower":"1","shareWay":"WechatMoments","taskStatus":"1"},{"id":"303","name":"用VBA写个小工具","subtitle":"副标题","picUrl":"/Uploads/task/58be5c829ca75.jpeg","content":"面谈","minShareTime":null,"maxShareTime":"5","completeFlag":"0","requiredFlags":"0","staff_flower":"1","shareWay":"WechatMoments","taskStatus":"1"}],"completeTask":null,"totalNum":1,"userActive":"0"}
     */

    private boolean success;
    private String msg;
    private DataBean data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * requiredTask : null
         * optionalTask : [{"id":"321","name":"京津冀国土去爷爷家","subtitle":null,"picUrl":"/Uploads/task/58c79d9521680.jpeg","content":"路","minShareTime":null,"maxShareTime":"5","completeFlag":"0","requiredFlags":"0","staff_flower":"1","shareWay":"WechatMoments","taskStatus":"1"},{"id":"303","name":"用VBA写个小工具","subtitle":"副标题","picUrl":"/Uploads/task/58be5c829ca75.jpeg","content":"面谈","minShareTime":null,"maxShareTime":"5","completeFlag":"0","requiredFlags":"0","staff_flower":"1","shareWay":"WechatMoments","taskStatus":"1"}]
         * completeTask : null
         * totalNum : 1
         * userActive : 0
         */

        private Object requiredTask;
        private Object completeTask;
        private int totalNum;
        private String userActive;
        private List<OptionalTaskBean> optionalTask;

        public Object getRequiredTask() {
            return requiredTask;
        }

        public void setRequiredTask(Object requiredTask) {
            this.requiredTask = requiredTask;
        }

        public Object getCompleteTask() {
            return completeTask;
        }

        public void setCompleteTask(Object completeTask) {
            this.completeTask = completeTask;
        }

        public int getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(int totalNum) {
            this.totalNum = totalNum;
        }

        public String getUserActive() {
            return userActive;
        }

        public void setUserActive(String userActive) {
            this.userActive = userActive;
        }

        public List<OptionalTaskBean> getOptionalTask() {
            return optionalTask;
        }

        public void setOptionalTask(List<OptionalTaskBean> optionalTask) {
            this.optionalTask = optionalTask;
        }

        public static class OptionalTaskBean {
            /**
             * id : 321
             * name : 京津冀国土去爷爷家
             * subtitle : null
             * picUrl : /Uploads/task/58c79d9521680.jpeg
             * content : 路
             * minShareTime : null
             * maxShareTime : 5
             * completeFlag : 0
             * requiredFlags : 0
             * staff_flower : 1
             * shareWay : WechatMoments
             * taskStatus : 1
             */

            private String id;
            private String name;
            private String subtitle;
            private String picUrl;
            private String content;
            private Object minShareTime;
            private String maxShareTime;
            private String completeFlag;
            private String requiredFlags;
            private String staff_flower;
            private String shareWay;
            private String taskStatus;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSubtitle() {
                return subtitle;
            }

            public void setSubtitle(String subtitle) {
                this.subtitle = subtitle;
            }

            public String getPicUrl() {
                return picUrl;
            }

            public void setPicUrl(String picUrl) {
                this.picUrl = picUrl;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public Object getMinShareTime() {
                return minShareTime;
            }

            public void setMinShareTime(Object minShareTime) {
                this.minShareTime = minShareTime;
            }

            public String getMaxShareTime() {
                return maxShareTime;
            }

            public void setMaxShareTime(String maxShareTime) {
                this.maxShareTime = maxShareTime;
            }

            public String getCompleteFlag() {
                return completeFlag;
            }

            public void setCompleteFlag(String completeFlag) {
                this.completeFlag = completeFlag;
            }

            public String getRequiredFlags() {
                return requiredFlags;
            }

            public void setRequiredFlags(String requiredFlags) {
                this.requiredFlags = requiredFlags;
            }

            public String getStaff_flower() {
                return staff_flower;
            }

            public void setStaff_flower(String staff_flower) {
                this.staff_flower = staff_flower;
            }

            public String getShareWay() {
                return shareWay;
            }

            public void setShareWay(String shareWay) {
                this.shareWay = shareWay;
            }

            public String getTaskStatus() {
                return taskStatus;
            }

            public void setTaskStatus(String taskStatus) {
                this.taskStatus = taskStatus;
            }
        }
    }
}
