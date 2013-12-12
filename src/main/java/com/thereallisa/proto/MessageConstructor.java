package com.thereallisa.proto;

import java.util.List;

import com.thereallisa.proto.JobMessageProto.JobMessage;

public class MessageConstructor {
	
	static enum PROTO_ARGS{USER_ID,REPORT_ID,FILE_URL}
	
	public static byte[] constructMessage(List<String> args){
		if(args.size() != 3)
			throw new IllegalArgumentException("Arguments expected: User ID, Report ID, File URL");
		
		JobMessage.Builder builder = JobMessage.newBuilder();
		
		builder.setUserID(Integer.parseInt(args.get(PROTO_ARGS.USER_ID.ordinal())));
		builder.setReportID(Integer.parseInt(args.get(PROTO_ARGS.REPORT_ID.ordinal())));
		builder.setFileLocation(args.get(PROTO_ARGS.FILE_URL.ordinal()));
		
		JobMessage message = builder.build();
		return message.toByteArray();
	}
}
