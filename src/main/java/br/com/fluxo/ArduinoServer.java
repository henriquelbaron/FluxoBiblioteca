package br.com.fluxo;

import java.io.InputStream;
import java.util.Date;

import com.fazecast.jSerialComm.SerialPort;

import br.com.fluxo.dao.BaseDao;
import br.com.fluxo.domain.Fluxo;

public class ArduinoServer {

	public static void main(String[] arg) throws Exception {
		String nomePorta = "/dev/ttyUSB0";
		int frequenciaSerial = 115200;
		SerialPort port = null;
		InputStream in = null;
		try {
			port = SerialPort.getCommPort(nomePorta);
			port.setComPortParameters(frequenciaSerial, 8, SerialPort.ONE_STOP_BIT, SerialPort.NO_PARITY);
			port.openPort();
			in = port.getInputStream();
			byte[] buffer = new byte[1024];
			BaseDao fluxoDao = new BaseDao();
			while (true) {
				Thread.sleep(10000);
				int len = in.read(buffer);
				if (len > 0) {
					String message = new String(buffer);
					if (!message.isBlank()) {
						String[] array = message.split("\n");
						for (int i = 0; i < array.length; i++) {
							if (!array[i].trim().isEmpty()) {
								Fluxo fluxo = new Fluxo();
								fluxo.setTimeStamp(new Date(System.currentTimeMillis()));
								if (array[i].trim().equals("1")) {
									fluxo.setDirecao(true);
								} else if (array[i].trim().equals("-1")) {
									fluxo.setDirecao(false);
								}
								fluxoDao.inserir(fluxo);
							}
						}
						System.out.println(message);
					}
				}
			}
		} finally {
			in.close();
			port.closePort();
		}
	}

}
