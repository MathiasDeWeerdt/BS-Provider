package org.bs;

import java.applet.Applet;
import java.net.URL;

import org.bs.stub.Stub;
import org.parabot.core.Context;
import org.parabot.core.asm.ASMClassLoader;
import org.parabot.environment.servers.ServerManifest;
import org.parabot.environment.servers.ServerProvider;
import org.parabot.environment.servers.Type;

@ServerManifest(author = "SingleCore", name = "Battlescape loader", type = Type.LOADER, version = 0.1)
public class Provider extends ServerProvider {

	@Override
	public Applet fetchApplet() {
		try {

			final Context context = Context.getInstance();
			final ASMClassLoader classLoader = context.getASMClassLoader();
			final Class<?> clientClass = classLoader.loadClass("client");

			Object instance = clientClass.newInstance();
			Applet applet = (Applet) instance;
			applet.setStub(new Stub());

			return applet;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public URL getJar() {
		try {
			return new URL("http://puu.sh/84iVd.jar");
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return null;
	}

}
