Summary

    * Status: Localization of portlet title
    * CCP Issue: CCP-479, Product Jira Issue: WEBOS-336.
    * Complexity: N/A

The Proposal
Problem description

What is the problem to fix?

    * Localization of portlet title in WebOS.

Fix description

How is the problem fixed?

    * Depends on PORTAL-3893, PORTAL-3920 and PORTAL-3922
    * For portlets that need to translate, add <show-edited-title>false</show-edited-title> into <application> tag in pages.xml file

Patch file: WEBOS-336.patch

Tests to perform

Reproduction test
* To reproduce this issue:

1/ Add an application
2/ Change language
3/ Sign out
4/ Login

* The language of the portlet doesn't change => the portlet's title is hard coded in the portlet.xml.
* The same problem exist for any portlet chosen from the page navigations: the language doesn't change => the portlet's title is hard coded in the page.xml

Tests performed at DevLevel
*

Tests performed at QA/Support Level
*

Documentation changes

Documentation changes:
* No

Configuration changes

Configuration changes:
* Yes, add <show-edited-title> tag into <application>

Will previous configuration continue to work?
*

Risks and impacts

Can this bug fix have any side effects on current client projects?

    * Function or ClassName change: no

Is there a performance risk/cost?
* No

Validation (PM/Support/QA)

PM Comment
* Patch validated on behalf of PM

Support Comment
* Patch validated.

QA Feedbacks
*

