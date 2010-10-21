Summary

    * Status: Problem of JavaScript in IE8
    * CCP Issue: CCP-622, Product Jira Issue: WEBOS-348
    * Complexity: N/A

The Proposal
Problem description

What is the problem to fix?
In the mode WebOS, when you open a portlet in IE8, a JavaScript error appears at the bottom of the browser. After a double click on this error and after choosing "Copy the error details", you have the detail of invalid argument in ImplodeExplode.js.

Fix description

How is the problem fixed?

    * In ImplodeExplode.js, doInit() and doCenterInit() functions try to use a value from undefined property of the current window portlet object "this.object.maxIndex " --> need to change to "this.object.style.zIndex" (only IE8 complains this bug, other browsers skip this error)

Patch information:

    * Final files to use should be attached to this page (Jira is for the discussion)

Patch file: WEBOS-348.patch

Tests to perform

Reproduction test
* cf above

Tests performed at DevLevel
* cf above

Tests performed at QA/Support Level
*

Documentation changes

Documentation changes:
* No

Configuration changes

Configuration changes:
* No

Will previous configuration continue to work?
* Yes
Risks and impacts

*Can this bug fix have an impact any side effects on current client projects?
* No

Is there a performance risk/cost?
* No

Validation (PM/Support/QA)

PM Comment

    * Validated on behalf of PM

Support Comment

    * Patch validated

QA Feedbacks
*

