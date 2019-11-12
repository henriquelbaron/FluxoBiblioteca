package br.com.fluxo;

import java.io.InputStream;
import java.util.Date;

import com.fazecast.jSerialComm.SerialPort;

import br.com.fluxo.dao.BaseDao;
import br.com.fluxo.domain.Fluxo;

public class ArduinoServer {

	public static void main(String[] arg) throws Exception {
		String nomePorta = "/dev/ttyUSB1";
		int frequenciaSerial = 9600;
		SerialPort port = null;
		InputStream in = null;
		try {
			port = SerialPort.getCommPort(nomePorta);
			port.setComPortParameters(frequenciaSerial, 8, SerialPort.ONE_STOP_BIT, SerialPort.NO_PARITY);
			port.openPort();
			in = port.getInputStream();
			byte[] buffer = new byte[1024];
			String message = "";
			BaseDao fluxoDao = new BaseDao();
			while (true) {
				int len = in.read(buffer);
				if (len > 0) {
					message = new String(buffer);
					System.out.println(message);
					Fluxo fluxo = new Fluxo();
					fluxo.setTimeStamp(new Date(System.currentTimeMillis()));
					try {
						Integer numero = Integer.parseInt(message.trim());
						fluxo.setPessoasDentro(numero);
					} catch (NumberFormatException e) {
						System.out.println(e);
					}
					if (fluxo.getPessoasDentro() != null) {
						fluxoDao.inserir(fluxo);
					}
				}
			}
		} finally {
			in.close();
			port.closePort();
			System.out.println("done");
		}
	}

}
