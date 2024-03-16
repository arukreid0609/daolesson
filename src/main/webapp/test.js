async function func() {
    let res = await fetch("Main");
    const t = await res.text();
    console.log(t);
}

func();