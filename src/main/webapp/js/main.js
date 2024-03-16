/**
 * 
 */
const users = document.getElementById("users");
getUsers();

async function getUsers() {
    // UsersDataへリクエスト、レスポンスをJson形式で受け取る
    let response = await fetch("UsersData");
    let root = await response.json();

    // 受け取った情報を表示する
    for (const user of root.result) {
        // tr要素を作成、table要素に追加
        const tr = document.createElement("tr");
        users.append(tr);

        // td要素を作成、先ほど作成したtr要素に追加
        for (const key in user) {
            const td = document.createElement("td");
            tr.append(td);
            td.textContent = user[key]; // オブジェクトのid,name,ageプロパティ
        }
        // users.innerHTML += `id:${user.id} 名前:${user.name} 年齢:${user.age}<br>`;
    }
}