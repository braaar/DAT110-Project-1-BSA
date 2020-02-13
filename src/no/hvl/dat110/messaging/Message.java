package no.hvl.dat110.messaging;

import java.util.Arrays;

public class Message {

	private byte[] payload;

	public Message(byte[] payload) {
		
		this.payload = payload; // TODO: check for length within boundary
	}

	public Message() {
		super();
	}

	public byte[] getData() {
		return this.payload; 
	}

	public byte[] encapsulate() {
		
		byte[] encoded = Arrays.copyOf(payload, 128);
		
		for(int i = 1; i <= payload.length; i++) {
			encoded[i] = payload[i-1];
		}		
		encoded[0] = (byte) payload.length;

		return encoded;
		
	}

	public void decapsulate(byte[] received) {
		payload = Arrays.copyOfRange(received, 1, received[0]+1);
	}
}
