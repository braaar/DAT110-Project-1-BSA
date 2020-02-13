package no.hvl.dat110.system.display;

import java.util.Arrays;

import no.hvl.dat110.rpc.RPCImpl;
import no.hvl.dat110.rpc.RPCUtils;

public class DisplayImpl implements RPCImpl {

	public void write(String message) {
		System.out.println("DISPLAY:" + message);
	}
	
	public byte[] invoke(byte[] request) {
		
		byte[] reply = Arrays.copyOfRange(request, 0, 1);		
		byte rpcid = request[0];
		
		String message = new String(Arrays.copyOfRange(request, 1, request.length));
		
		write(message);
		RPCUtils.marshallVoid(rpcid);

		return reply;
	}
}
