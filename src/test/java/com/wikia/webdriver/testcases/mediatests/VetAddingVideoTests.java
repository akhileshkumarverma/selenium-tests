package com.wikia.webdriver.testcases.mediatests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.VideoContent;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.minieditor.MiniEditorComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetAddVideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetOptionsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.messagewall.NewMessageWall;

import org.testng.annotations.Test;

/**
 * Documentation: https://docs.google.com/a/wikia-inc.com/spreadsheet/ccc?key=0
 * AtG89yMxyGSadEtPY28ydDB4czkydXNmMkJVQ2NGR0E#gid=7
 * @ownership Content X-Wing
 */

public class VetAddingVideoTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();

  @Test(groups = {"VetAddVideo_001", "VetTests", "VetAddVideo", "Media"})
  public void VetAddVideo_001_MessageWallProvider() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userName, credentials.password, wikiURL);
    NewMessageWall wall = base.openMessageWall(credentials.userName, wikiURL);
    String message = PageContent.MESSAGE_WALL_MESSAGE_PREFIX + wall.getTimeStamp();
    String title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
    MiniEditorComponentObject mini = wall.triggerMessageArea();
    wall.clickBoldButton();
    mini.switchAndWrite(message);
    wall.writeTitle(title);
    VetAddVideoComponentObject vetAddingVideo = mini.clickAddVideo();
    VetOptionsComponentObject vetOptions =
        vetAddingVideo.addVideoByUrl(VideoContent.YOUTUBE_VIDEO_URL3);
    vetOptions.setCaption(PageContent.CAPTION);
    vetOptions.submit();
    mini.verifyVideoMiniEditor();
    wall.submit();
    wall.verifyPostedMessageVideo(title);
  }

  @Test(groups = {"VetAddVideo_002", "VetTests", "VetAddVideo", "Media"})
  public void VetAddVideo_002_MessageWallLibrary() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userName, credentials.password, wikiURL);
    NewMessageWall wall = base.openMessageWall(credentials.userName, wikiURL);
    String message = PageContent.MESSAGE_WALL_MESSAGE_PREFIX + wall.getTimeStamp();
    String title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
    MiniEditorComponentObject mini = wall.triggerMessageArea();
    wall.clickBoldButton();
    mini.switchAndWrite(message);
    wall.writeTitle(title);
    VetAddVideoComponentObject vetAddingVideo = mini.clickAddVideo();
    VetOptionsComponentObject vetOptions =
        vetAddingVideo.addVideoByQuery(VideoContent.WIKIA_VIDEO_QUERY, 0);
    vetOptions.setCaption(PageContent.CAPTION);
    vetOptions.submit();
    mini.verifyVideoMiniEditor();
    wall.submit();
    wall.verifyPostedMessageVideo(title);
  }
}
