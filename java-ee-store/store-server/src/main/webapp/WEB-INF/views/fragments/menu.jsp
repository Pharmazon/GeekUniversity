<table width="100%" cellspacing="0" cellpadding="0" border="0">
    <tr>
        <td width="0" nowrap="nowrap">&nbsp;</td>
        <td>
            <form action="${param.get("action")}">
                <button type="submit">Add</button>
            </form>
        </td>
        <td width="0" nowrap="nowrap">&nbsp;</td>
        <td>
            <form>
                <input type="hidden" name="time" value="<%=System.currentTimeMillis()%>">
                <button type="submit">Refresh</button>
            </form>
        </td>
        <td width="100%">&nbsp;</td>
    </tr>
</table>