package com.uv.audio.netty;

/**
 * Created by clark on 2017/5/11.
 * 放can协议中的各种常量值
 */
public interface CANDict {

    /**
     * 外层协议部分
     */
    byte FrameHead = (byte) 0xAA;//头
    byte FrameTail = 0x55;//尾
    byte PROTOCOL_SWITCH = (byte) 0xA5;
    byte[] PROTOCOL_HEAD = new byte[]{FrameHead, FrameHead};
    byte[] PROTOCOL_TAIL = new byte[]{FrameTail, FrameTail};
    int PROTOCOL_PROPERTY = 0x0;
    int PROTOCOL_FRAME_TYPE = 0x1;
    int PROTOCOL_MESSAGE_TYPE = 0x0;
    int PROTOCOL_LEN = 21;
    byte[] PROTOCOL_KEY_WORD = new byte[]{(byte) 0xA5, (byte) 0xAA, 0x55};
    /**
     * CAN协议部分
     */
    int CAN_FRAME_LEN = 12;
    int CAN_FRAME_MAC = 0;
    int CAN_FRAME_ID = 1;
    int CAN_SINGLE_FRAME = 0;
    int CAN_EXTEND = 1;
    int CAN_MULTI_FRAME_FIRST = 2;
    int CAN_MULTI_FRAME_OTHER = 3;

    //MAC类命令
    int CMD_MAC_EASY_USE = 1;
    int CMD_MAC_MANAGER_CONFIRM_INTERFACE = 2;
    int CMD_MAC_SENSOR_CONFIRM_IP = 3;
    //ID类命令
    int CMD_ID_MANAGER_FETCH_SENSOR_PARAM = 1;//系统要参数
    int CMD_ID_SENSOR_PARAM_SEND = 2;//设备回参数
    int CMD_ID_MANAGER_CONFIG_SENSOR_PARAM = 3;//系统配置参数
    int CMD_ID_SYSTEM_QUERY_SENSOR_OUTLINE_HISDATA = 4;//系统查询设备历史概要数据命令
    int CMD_ID_SENSOR_OUTLINE_HISDATA = 5;//设备历史概要数据命令
    int CMD_ID_SYSTEM_QUERY_SENSOR_DETAIL_HISDATA = 6;//系统查询历史详细数据命令
    int CMD_ID_SENSOR_DETAIL_HISDATA = 7;//设备发送历史详细数据命令
    int CMD_ID_SYSTEM_PERSON_POSITION_BROADCAST = 8;//人员定位数据广播
    int CMD_ID_GAS_REALTIME_DATA = 9;//瓦斯抽放虚拟测点实时数据命令
    int CMD_ID_SENSOR_STATUS_CHANGE = 10;//设备状态变更命令
    int CMD_ID_SYSTEM_HAND_CONTROL = 11;//系统手控设备命令
    int CMD_ID_SENSOR_AUTO_CONTROL = 12;//设备自动控制执行器命令
    int CMD_ID_SENSOR_CONFIG_SENSOR_PARAM = 13;//设备配置设备参数命令
    int CMD_ID_SYSTEM_CONFIG_SENSOR_CALIBRATE_STATUS = 14;//系统控制设备标校状态变更命令
    int CMD_ID_SENSOR_CALIBRATE_STATUS = 15;//设备标校状态变更命令
    int CMD_ID_DELETE_SENSOR = 16;//系统设备删除命令

    int CMD_ID_SENSOR_POWER_HISDATA_INFO = 53;//电源箱信息历史数据命令
    int CMD_ID_STATION_REALTIME_DATA = 54;//分站实时数据
    int CMD_ID_SENSOR_PERSON_POSITION_HISTORY_REALTIME_DATA = 55;//人员定位历史数据上传
    int CMD_ID_SENSOR_HISTORY_REALTIME_DATA = 56;//设备历史数据命令
    int CMD_ID_SENSOR_HISTORY_GAS_REALTIME_DATA = 57;//瓦斯抽放虚拟测点历史数据
    int CMD_ID_SENSOR_HISTORY_STATUS_CHANGE = 58;//控制器历史状态变更命令
    int CMD_ID_SENSOR_POWER_REALTIME_INFO = 59;//电源箱信息实时数据命令
    int CMD_ID_SENSOR_POWER_SELF_TEST = 52;//电源箱信息实时数据命令

    int CMD_ID_SYSTEM_INIT_TIME = 60;//系统初始化设备系统时间命令
    int CMD_ID_SENSOR_PERSON_POSITION_UPLOAD = 61;//人员定位数据上传
    int CMD_ID_REALTIME_DATA = 62;//实时数据
    int CMD_ID_ACK = 63;//ack
    int CMD_ID_RELA_SENSOR_REALTIME_DATA = 12;//关联设备实时数据命令
    int CMD_ID_AUDIO_DATA = 32;//语音传输命令
    int CMD_ID_ERROR_RATE_TEST = 34;// 误码率测试命令

    int NET_GET_HEAD = 136;
    int MANAGER_HEAD = -128;

    //设备参数传递key值
    int PARAM_KEY_FOR_SENSOR_CODE = 0x1;
    int PARAM_KEY_FOR_SIGNAL_TYPE = 0x2;
    int PARAM_KEY_FOR_CONTROL_FLAG = 0x3;
    int PARAM_KEY_FOR_RANGE_INFO = 0x4;
    int PARAM_KEY_FOR_ALARM_INFO = 0x6;
    int PARAM_KEY_FOR_STATION_CODE = 0x8;
    int PARAM_KEY_FOR_DEVICE_CODE = 0x9;
    int PARAM_KEY_FOR_LOGIC_INFO = 0X10;
    int PARAM_KEY_FOR_LOGIC_STATUS = 0X11;
    int PARAM_KEY_FOR_CONTROL_SENSOR_ID = 0X12;
    int PARAM_KEY_FOR_SIMULATESTATION_CONF = 0x13;
    int PARAM_KEY_FOR_ALARM_LEVEL_TIME = 0x14;
    int PARAM_KEY_FOR_GAS_CONF = 0X0A;
    int PARAM_KEY_FOR_GAS_RESET = 0X0B;
    int PARAM_KEY_FOR_NEGATIVE_DRIFT = 0x0C;
    /**
     * 设备参数
     */
    String PARAM_HEIGHTRANGE = "HeightRange";
    String PARAM_LOWRANGE = "LowRange";
    String PARAM_ALARMLIST = "AlarmList";
    String PARAM_ALARM_LEVEL_TIME_LIST = "AlarmLevelTimeList";

    String PARAM_NEGATIVE_DRIFT = "NegativeDrift";
    //瓦斯抽放
    String GAS_PARAM_ID = "sensorObj_id";//虚拟测点
    String GAS_PARAM_SENSOR_NO = "sensorNo";
    String GAS_PARAM_FLOW = "flow";//流量
    String GAS_PARAM_PRESSURE = "pressure";//压力
    String GAS_PARAM_METHANE = "methane";//甲烷
    String GAS_PARAM_TEMPRATURE = "temperature";//温度
    String GAS_PARAM_DIAMETER = "diameter";//直径
    String GAS_PARAM_COEFFICIENT = "coefficient";//系数
    String GAS_PARAM_OPR = "oprCode";//操作代码


    /**
     * 分站code
     */
    int SENSOR_CODE_FOR_STATION = 1000;
    /**
     * 瓦斯抽放分站code
     */
    int SENSOR_CODE_FOR_GAS_STATION = 1002;
    /**
     * 中继设备
     */
    int SENSOR_CODE_FOR_RELAY = 1001;
    /**
     * 小分站
     */
    int SENSOR_CODE_FOR_SIMULATE_STATION = 1003;

    int SENSOR_CODE_FOR_WIRELESS_STATION=1004;
    /**
     * 人员定位基站
     */
    int SENSOR_CODE_FOR_PERSON_STATION = 2000;
    /**
     * 井下音箱设备
     */
    int SENSOR_CODE_FOR_MINE_AUDIO = 3000;
    /**
     * 地面音箱设备
     */
    int SENSOR_CODE_FOR_GROUND_AUDIO = 3001;
    /**
     * 井下报警音响
     */
    int SENSOR_CODE_FOR_MINE_ALARM_AUDIO = 3002;
    /**
     * 地面报警音响
     */
    int SENSOR_CODE_FOR_GROUND_AlARM_AUDIO = 3003;
    /**
     * 瓦斯抽放虚拟测点code
     */
    int SENSOR_CODE_FOR_GAS = 900;
    /**
     * 两态开停
     */
    int SENSOR_CODE_FOR_TWO_SWITCH = 10;
    /**
     * 三态开停
     */
    int SENSOR_CODE_FOR_THREE_SWITCH = 11;
    /**
     * 断电器
     */
    int SENSOR_CODE_FOR_CONTROL = 12;
    /**
     * 报警器
     */
    int SENSOR_CODE_FOR_ALARM = 13;

    /**
     * 矿用供水传感器
     */
    int SENSOR_CODE_FOR_WATER = 15;

    /**
     * 双回路切换装置
     */
    int SENSOR_CODE_FOR_TWO_CIRCUIT = 35;

    /**
     * 两态开停量正反方向传感器
     */
    int SENSOR_CODE_FOR_DIRECTION = 36;
    /**
     * can协议中mac值的最大值,即3字节最大值
     */
    int MAX_REAL_MAC = 0xFFFFFF;
    /**
     * can协议中ID最大值,即2字节最大值
     */
    int MAX_REAL_ID = 0xFFFF;

    /**
     * 电源箱参数传递key值
     */
    int POWER_KEY_FOR_CONTROL_STATUS = 0x1;
    int POWER_KEY_FOR_TOTAL_VOLTAGE_1 = 0x10;
    int POWER_KEY_FOR_CHARGE_CURRENT_1 = 0x11;
    int POWER_KEY_FOR_DISCHARGE_CURRENT_1 = 0x12;
    int POWER_KEY_FOR_CAPACITY_1 = 0x13;
    int POWER_KEY_FOR_CAPACITY_PERCENT_1 = 0x14;
    int POWER_KEY_FOR_BATTERY_INFO_1 = 0x15;

    int POWER_KEY_FOR_TOTAL_VOLTAGE_2 = 0x20;
    int POWER_KEY_FOR_CHARGE_CURRENT_2 = 0x21;
    int POWER_KEY_FOR_DISCHARGE_CURRENT_2 = 0x22;
    int POWER_KEY_FOR_CAPACITY_2 = 0x23;
    int POWER_KEY_FOR_CAPACITY_PERCENT_2 = 0x24;
    int POWER_KEY_FOR_BATTERY_INFO_2 = 0x25;

    int POWER_KEY_FOR_POWER_SUPPLY_MODULE_U_1 = 0X30;
    int POWER_KEY_FOR_POWER_SUPPLY_MODULE_I_1 = 0X31;
    int POWER_KEY_FOR_POWER_SUPPLY_MODULE_U_2 = 0X32;
    int POWER_KEY_FOR_POWER_SUPPLY_MODULE_I_2 = 0x33;
    int POWER_KEY_FOR_POWER_SUPPLY_MODULE_U_3 = 0X34;
    int POWER_KEY_FOR_POWER_SUPPLY_MODULE_I_3 = 0X35;
    int POWER_KEY_FOR_POWER_SUPPLY_MODULE_U_4 = 0x36;
    int POWER_KEY_FOR_POWER_SUPPLY_MODULE_I_4 = 0x37;
    int POWER_KEY_FOR_POWER_SUPPLY_MODULE_U_5 = 0x38;
    int POWER_KEY_FOR_POWER_SUPPLY_MODULE_I_5 = 0x39;
    int POWER_KEY_FOR_POWER_SUPPLY_MODULE_U_6 = 0x3A;
    int POWER_KEY_FOR_POWER_SUPPLY_MODULE_I_6 = 0x3B;

    int POWER_KEY_FOR_ALTERNATING_CURRENT_U = 0x80;
    int POWER_KEY_FOR_ALTERNATING_CURRENT_I = 0x81;

}
