var app_mng_id = 'APP-00021';

var ws_req_url = 'processWsCall.do';

var makeWsParamStr = function(ws_oper_id_to_call, req_xml_to_send) {
	return 'wsOperId=' + ws_oper_id_to_call + '&reqXml=' 
		+ '<runQueryAsAService xmlns="${tns}"><login>${login}</login><password>${password}</password><회사코드>${회사코드}</회사코드><스케일링_계수>${스케일링_계수}</스케일링_계수>' 
		+ req_xml_to_send 
		+ '</runQueryAsAService>';
};
