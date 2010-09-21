/*
 * Copyright (C) 2009 eXo Platform SAS.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.exoplatform.webos.services.desktop.impl;

import java.io.InputStream;

import org.chromattic.api.Chromattic;
import org.chromattic.api.ChromatticSession;
import org.chromattic.common.IO;
import org.chromattic.ext.ntdef.NTFolder;
import org.chromattic.ext.ntdef.Resource;
import org.exoplatform.commons.chromattic.ChromatticLifeCycle;
import org.exoplatform.commons.chromattic.ChromatticManager;
import org.exoplatform.container.xml.InitParams;
import org.exoplatform.webos.services.desktop.DesktopBackgroundService;

/**
 * @author <a href="mailto:hoang281283@gmail.com">Minh Hoang TO</a>
 * Sep 14, 2010
 */

public class DesktopBackgroundServiceImpl implements DesktopBackgroundService
{

   private ChromatticManager chromatticManager;

   private ChromatticLifeCycle chromatticLifecycle;

   private DesktopBackgroundRegistry backgroundRegistry;

   public DesktopBackgroundServiceImpl(ChromatticManager manager, InitParams params)
   {
      chromatticManager = manager;
      chromatticLifecycle = (WebOSChromatticLifecycle) manager.getLifeCycle("webos");

      initBackgroundRegistry();
   }

   public void initBackgroundRegistry()
   {
      if (backgroundRegistry == null)
      {
         Chromattic chromattic = chromatticLifecycle.getChromattic();
         ChromatticSession session = chromattic.openSession();

         backgroundRegistry = session.findByPath(DesktopBackgroundRegistry.class, "webos:desktopBackgroundRegistry");
         if (backgroundRegistry == null)
         {
            backgroundRegistry = session.insert(DesktopBackgroundRegistry.class, "webos:desktopBackgroundRegistry");
         }
      }
   }

   public ChromatticLifeCycle getChromatticLifecycle()
   {
      return this.chromatticLifecycle;
   }

   @Override
   public boolean removeBackgroundImage(String userName, String backgroundImageName)
   {
      PersonalBackgroundSpace space = backgroundRegistry.getPersonalBackgroundSpace(userName);
      if (space == null)
      {
         //TODO: Throws an exception here
         return false;
      }

      space.getBackgroundImageFolder().addChild(backgroundImageName, null);
      return true;
   }

   @Override
   public void selectBackgroundImage(String userName, String backgroundName)
   {
      PersonalBackgroundSpace space = backgroundRegistry.getPersonalBackgroundSpace(userName);

      if (space != null)
      {
         space.setCurrentBackground(backgroundName);
      }

   }

   @Override
   public boolean uploadBackgroundImage(String userName, String backgroundImageName, String mimeType, String encoding,
         InputStream binaryStream)
   {
      PersonalBackgroundSpace space = backgroundRegistry.getPersonalBackgroundSpace(userName);
      if (space == null)
      {
         return false;
      }

      try
      {
         NTFolder backgroundImageFolder = space.getBackgroundImageFolder();
         byte[] content = IO.getBytes(binaryStream);
         backgroundImageFolder.createFile(backgroundImageName, new Resource(mimeType, encoding, content));

         return true;
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
         return false;
      }

   }

   @Override
   public String getCurrentBackgroundImageURL(String userName)
   {
      PersonalBackgroundSpace space = backgroundRegistry.getPersonalBackgroundSpace(userName);
      if (space == null)
      {
         return null;
      }

      return "jcr/" + chromatticLifecycle.getRepositoryName() + "/" + chromatticLifecycle.getWorkspaceName()
            + "/webos:desktopBackgroundRegistry/webos:" + space.getTitle() + "/"
            + (space.getCurrentBackground() == null ? "default" : space.getCurrentBackground());
   }
}
