# pip install reuests json warnings
import requests 
import json
import warnings
warnings.filterwarnings("ignore")  # Suppressing InsecureRequestWarning

url = "https://tso-data-gw.pttplc.com/api/PMIS/SCADA/v1/ARCHMapping"
payload = json.dumps({
    "Period": "Day",
    "From": "28-01-2024 00:00",
    "To": "29-01-2024 00:00",
    "Profile": "dude"
    })
headers = {'Content-Type': 'application/json','KeyId': '019ec11a-6c41-4b39-b3f5-356aa973b474'}

def make_post_request(url, payload):
    try:
        response = requests.request("POST", url, headers=headers, data=payload,verify=False)
        return response.text
    except requests.exceptions.RequestException as e:
        print(e)
        return None
    
data = make_post_request(url, payload)
if data:
    print(data)
else:
    print("Failed to request")