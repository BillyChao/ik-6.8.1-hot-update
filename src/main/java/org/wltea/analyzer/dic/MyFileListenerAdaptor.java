package org.wltea.analyzer.dic;

import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.logging.log4j.Logger;
import org.wltea.analyzer.help.ESPluginLoggerFactory;

import java.io.File;

public class MyFileListenerAdaptor extends FileAlterationListenerAdaptor {
    private static final Logger logger = ESPluginLoggerFactory.getLogger(MyFileListenerAdaptor.class.getName());

    @Override
    public void onFileChange(File file) {
        logger.info("[==========]reload hot dict from myes.dic......");
        Dictionary.getSingleton().reLoadMainDict();
        //System.out.println("[==========]reload hot dict from myes.dic......");
        super.onFileChange(file);
    }

    @Override
    public void onStart(FileAlterationObserver observer) {
        //System.out.println("[==========]start mointor dic change......");
        super.onStart(observer);
    }

    @Override
    public void onStop(FileAlterationObserver observer) {
        //System.out.println("[==========]stop mointor dic change......");
        super.onStop(observer);
    }
}
