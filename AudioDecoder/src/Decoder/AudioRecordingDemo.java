package Decoder;

import net.rim.blackberry.api.mail.BodyPart.ContentType;
import net.rim.device.api.system.Application;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.container.*;
import net.rim.device.api.ui.component.*;

import net.rim.device.api.ui.component.LabelField;
import javax.microedition.media.*;

import java.io.*;

import javax.microedition.media.control.*;

public class AudioRecordingDemo extends UiApplication {
	public static void main(String[] args) {
		AudioRecordingDemo app = new AudioRecordingDemo();
		app.enterEventDispatcher();
	}

	public AudioRecordingDemo() {
		pushScreen(new AudioRecordingDemoScreen());
	}

	private class AudioRecordingDemoScreen extends MainScreen implements
			IEventHandler {
		private AudioRecorderThread _recorderThread;
		private LabelField dtmf = new LabelField("DTMF:\n");
		private EditField textField  = new EditField("Code:","1234567890ABCD*#");
		private NumericChoiceField freqImpField  = new NumericChoiceField("Feq of Imp:",1,100,1,19);
		private NumericChoiceField freqOffset  = new NumericChoiceField("Feq offset:",1,4,1,0);
		private AudioRecordingDemoScreen thisref;

		public AudioRecordingDemoScreen() {
			// addGlobalEventListener(this);
			setTitle("Audio recording demo");
			addMenuItem(new StartRecording());
			addMenuItem(new StopRecording());
			addMenuItem(new StartPlay());

			add(dtmf);
			add(textField);
			add(freqImpField);
			add(freqOffset);
			thisref = this;

		}

		// ------------------------------------------------------------------------------

		private class StartPlay extends MenuItem {
			public StartPlay() {
				super("Play", 0, 100);
			}

			public void run() {
				try {
					AudioPlayThread playThread = new AudioPlayThread();
					playThread.start();
				} catch (Exception e) {
					Dialog.alert(e.toString());
				}
			}
		}

		private class AudioPlayThread extends Thread {
			private Player _player;
			private VolumeControl volume;
			private InputStream inputStream;

			AudioPlayThread() {
			}

			public void run() {
				try {
					Encoder.offset = (short) freqOffset.getSelectedValue();
					inputStream = GeneratedInputStreamFactory.getInstance(textField.getText(), freqImpField.getSelectedValue() );
					_player = javax.microedition.media.Manager.createPlayer(
							inputStream, ContentType.TYPE_AUDIO_BASIC_STRING);
					_player.realize();
					volume = (VolumeControl) _player
							.getControl("VolumeControl");
					volume.setLevel(100);
					_player.prefetch();
					_player.start();

				} catch (IOException e) {
					Dialog.alert(e.toString());
				} catch (MediaException e) {
					Dialog.alert(e.toString());
				}
			}

		}

		// ------------------------------------------------------------------------------
		private class StartRecording extends MenuItem {
			public StartRecording() {
				super("Start recording", 0, 100);
			}

			public void run() {
				try {
					AudioRecorderThread newRecorderThread = new AudioRecorderThread();
					dtmf.setText("DTMF:\n");
					newRecorderThread.start();
					_recorderThread = newRecorderThread;
				} catch (Exception e) {
					Dialog.alert(e.toString());
				}
			}
		}

		private class StopRecording extends MenuItem {
			public StopRecording() {
				super("Stop recording", 0, 100);
			}

			public void run() {

				// "Mary Had A Little Lamb" has "ABAC" structure
				// Use block to repeat "A" section
				// Tempos ranging from 20 to 508 beats per minute are divided by
				// 4
				// to create a tempo modifier range of 5 to 127.
				byte tempo_mod = 30; // 120 bpm
				// Note duration ranges from 128 (1/2 note) to 0 (128th of a
				// note)
				// with a default resolution of 64.
				byte duration = 8; // Note length 8 (quaver) = 1/8th of a note
									// duration
				// Notes are determined from ToneControl.C4 (Middle C),
				// which has a value of 60 and a frequency of 261.6 Hz.
				byte C4 = ToneControl.C4; // C note value = 60 (middle C)
				byte D4 = (byte) (C4 + 2); // D note value = 62 (a whole step)
				byte E4 = (byte) (C4 + 4); // E note value = 64 (a major third)
				byte G4 = (byte) (C4 + 7); // G note value = 67 (a fifth)
				byte rest = ToneControl.SILENCE; // rest
				byte[] mySequence = {
						ToneControl.VERSION,
						1, // version 1
						ToneControl.TEMPO,
						tempo_mod,
						//
						// Start define "A" section
						ToneControl.BLOCK_START,
						0,
						//
						// Content of "A" section
						E4,
						duration,
						D4,
						duration,
						C4,
						duration,
						E4,
						duration,
						E4,
						duration,
						E4,
						duration,
						E4,
						duration,
						rest,
						duration,
						//
						// End define "A" section
						ToneControl.BLOCK_END,
						0, // end of block number 0
							//
							// Play "A" section
						ToneControl.PLAY_BLOCK,
						0,
						//
						// Play "B" section
						D4, duration, D4, duration, D4, duration, rest,
						duration, E4, duration, G4, duration, G4, duration,
						rest,
						duration,
						//
						// Repeat "A" section
						ToneControl.PLAY_BLOCK,
						0,
						//
						// Play "C" section
						D4, duration, D4, duration, E4, duration, D4, duration,
						C4, duration };
				try {
					Player p = Manager
							.createPlayer(Manager.TONE_DEVICE_LOCATOR);
					p.realize();
					ToneControl c = (ToneControl) p.getControl("ToneControl");
					c.setSequence(mySequence);
					p.start();
				} catch (IOException ioe) {
				} catch (MediaException e) {
					Dialog.alert(e.toString());
				}

				try {
					dtmf.setText("DTMF:\n");
					if (_recorderThread != null) {
						_recorderThread.stop();
					}
				} catch (Exception e) {
					Dialog.alert(e.toString());
				}
			}
		}

		private class AudioRecorderThread extends Thread implements
				javax.microedition.media.PlayerListener {
			private Player _player;
			private RecordControl _recordControl;
			int frameSize = 512;
			private DecoderOutputStream outputStream;

			AudioRecorderThread() {
			}

			public void run() {
				try {
					Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
					Encoder.offset = (short) freqOffset.getSelectedValue();
					outputStream = new DecoderOutputStream(frameSize);
					
					_player = javax.microedition.media.Manager
							.createPlayer("capture://audio?encoding=audio/basic");
					_player.addPlayerListener(this);
					_player.realize();
					_recordControl = (RecordControl) _player
							.getControl("RecordControl");
					// _recordControl
					// .setRecordLocation("file:////home/user/AudioRecordingTest.wav");
					// .setRecordLocation("file:////SDCard/AudioRecordingTest.wav");
					outputStream.setHandler(thisref);
					_recordControl.setRecordStream(outputStream);
					_recordControl.startRecord();
					_player.start();

				} catch (IOException e) {
					// Dialog.alert(e.toString());
					System.out.println(e);
				} catch (MediaException e) {
					// Dialog.alert(e.toString());
					System.out.println(e);
				}
			}

			public void stop() {
				if (_player != null) {
					_player.close();
					_player = null;
				}
				if (_recordControl != null) {
					_recordControl.stopRecord();

					try {
						_recordControl.commit();
					} catch (Exception e) {
						Dialog.alert(e.toString());
					}
					_recordControl = null;
				}
			}

			public void playerUpdate(Player player, String event,
					Object eventData) {
				System.out.println("Player " + player.hashCode()
						+ " got event " + event + ": " + eventData);
			}
		}

		public void eventHandler(String s) {
			synchronized (Application.getEventLock()) {
				dtmf.setText(dtmf.getText()+","+s);
			}

		}
	}
}