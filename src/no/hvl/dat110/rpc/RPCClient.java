package no.hvl.dat110.rpc;

import no.hvl.dat110.messaging.*;

public class RPCClient {

	private MessagingClient msgclient;
	private Connection connection;
	
	public RPCClient(String server, int port) {
	
		msgclient = new MessagingClient(server,port);
	}
	
	public void register(RPCStub remote) {
		remote.register(this);
	}
	
	public void connect() {
		connection = msgclient.connect();	
	}
	
	public void disconnect() {
		connection.close();
	}
	
	public byte[] call(byte[] rpcrequest) {
		
		byte[] rpcreply;
		
		Message message = new Message(rpcrequest);

		connection.send(message);
		Message reply = new Message();
		boolean received = false;
		
		while(!received) {
			reply = connection.receive();
			if(reply.getData() != null)
				received = true;
		}
		
		rpcreply = reply.getData();
		
		return rpcreply;
		
	}

}
