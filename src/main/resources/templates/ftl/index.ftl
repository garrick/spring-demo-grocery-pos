<#-- @implicitly included -->
<#-- @ftlvariable name="model.items" type="org.commandline.grocerypos.dto.ItemList" -->
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <title>Canvas example</title>
    <link href="https://www.jqueryscript.net/css/jquerysctipttop.css" rel="stylesheet" type="text/css">
    <style>
        body {
            font-family: 'Roboto Condensed';
        }

        .container {
            padding: 10px;
            border: 2px solid #F90;
            height: 500px;
            margin: 50px auto;
            background: repeating-linear-gradient(0deg, rgb(221, 221, 221) 0px, rgb(221, 221, 221) 1px, transparent 1px, transparent 21px), repeating-linear-gradient(90deg, rgb(221, 221, 221) 0px, rgb(221, 221, 221) 1px, transparent 1px, transparent 21px), linear-gradient(90deg, hsl(104, 0%, 96%), hsl(104, 0%, 96%));
        }

        h1 {
            margin-top: 150px;
            text-align: center;
        }

        .draggable {
            width: 140px;
            height: 140px;
            background: #F90;
            border-radius: 10px;
            margin: 0 10px 10px 0;
            float: left;
        }

        .draggable.is-pointer-down {
            background: #09F;
            z-index: 2; /* above other draggies */
        }

        .draggable.is-dragging {
            opacity: 0.7;
        }

        #wrapper {
            width: 60%;
            margin: 0 auto;
            text-align: center;
        }

        #canvas {
            border: 1px solid black;
        }
    </style>
</head>
<body>
<h1>Hello, POS system!</h1>
<ol>
<#list model.itemList.currentItemIds as anId>
<li>
    Item: #${anId}
</li>
</#list>
</ol>
<form action="/index" method="post">
    <input type="hidden" name="currentItemIds" value="${model.itemList.streamOut}"/>
    <input type="text" name="nextItemId" value=""/>
    <input type="submit" value="Add Item"/>
</form>
</body>
</html>